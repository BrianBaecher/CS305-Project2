package com.snhu.sslserver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
}

@RestController
class ServerController {
	private static final String VALID_HASH_ALGO = "SHA-256";
	private static final String DATA = "Brian Baecher";

	private String _hashedValueHex; // Hex string of DATA's hashed value, assigned in constructor.
	private String _algoUsed;

	public ServerController() {
		hashStaticData(VALID_HASH_ALGO);
	}

	// route to enable check sum return of static data.
	@GetMapping("/hash")
	public String GetHashedData() {
		if (_hashedValueHex == null || _algoUsed.equals("ERROR")) {
			return "<p>Sorry, something's gone wrong</p>"; // if ctor does not successfully get instance of message
															// digest
		}

		String verifyRelativeURL = "verify?value=" + _hashedValueHex;
		return "<p>data: " + DATA + "<br />" + "Name of Cipher Algorithm Used: " + _algoUsed + "<br />"
				+ "CheckSum Value: " + _hashedValueHex + "<br/>" + "<br/>"
				+ "Click the link to see that the hash value is consistent: " + "<a href=" + verifyRelativeURL + ">"
				+ "click me" + "<a/>" + "<p/>";
	}

	@GetMapping("/verify")
	public String GetVerification(@RequestParam(value = "value", defaultValue = "") String value) {

		// using a hash algo other than SHA-256 will need a different sort of validation
		// regex check for hex-allowed chars, and string length 64.
		if (!value.matches("[0-9A-Fa-f]{64}")) {
			return "Bad input";
		}

		return "<p>" + "value: " + value + "<br/>" + "stored: " + _hashedValueHex + "<br/>" + "Is Match: "
				+ _hashedValueHex.equals(value) + "</p>";
	}

	private void hashStaticData(String hashAlgo) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(hashAlgo);
			_algoUsed = hashAlgo;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// In case of NoSuchAlgorithmException, use the fallback.
			try {
				messageDigest = MessageDigest.getInstance(VALID_HASH_ALGO);
				_algoUsed = VALID_HASH_ALGO;
			} catch (NoSuchAlgorithmException fallbackError) {
				// if even the static option is invalid, the service will not be able to respond
				// properly.
				System.err.println("Critical failure: Fallback hash algorithm (" + VALID_HASH_ALGO
						+ ") is not compatible with MessageDigest.");
				_algoUsed = "ERROR";
				fallbackError.printStackTrace();
			}
		}
		if (messageDigest != null) {
			byte[] dataAsByteArr = DATA.getBytes(StandardCharsets.UTF_8);
			messageDigest.update(dataAsByteArr);
			byte[] hashArr = messageDigest.digest();
			_hashedValueHex = ByteArrToHexString(hashArr);
		}
	}

	private String ByteArrToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();

		for (byte b : bytes) {
			String s = String.format("%02X", b);
			sb.append(s);
		}

		return sb.toString();
	}
}
