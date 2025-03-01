# CS305-Project2

# Briefly summarize your client, Artemis Financial, and its software requirements. Who was the client? What issue did the company want you to address?
A fictional financial advising service, whose needs were incredibly vague and ill-defined wanted to create an application that allowed for their clients to interface with their company's services. Due to the nature of their business (financial sector) security was a chief concern for them.

# What did you do well when you found your client’s software security vulnerabilities? Why is it important to code securely? What value does software security add to a company’s overall well-being?
Coding securely is essential to prevent vulnerabilities that could lead to unauthorized access/data breaches. Strong software security protects a company’s assets, is often a matter of regulatory compliance, and mitigates risks to users.

# Which part of the vulnerability assessment was challenging or helpful to you?
Reading through the identified problems and solutions to issues from the NVD database, I frequently came across issues that I did not have the prerequisite knowledge to understand. Being able to effectively research the issues, what the components involved are/what they do, is a useful excercise.

# How did you increase layers of security? In the future, what would you use to assess vulnerabilities and decide which mitigation techniques to use?
The layers of security we added are primarily the enabling of https connectivity through the
creation of our certificate via the keytool, and the encryption scheme functionality provided by java's
MessageDigest class. I've been focusing on using C#/.NET, so in the future I'd be using tools similar to java's dependency-check tool like the NuGet package manager's auditing tool, OWASP also provides a similar tool for the .NET ecosystem, but from what I've read it's not quite as fleshed out as the java version.

# How did you make certain the code and software application were functional and secure? After refactoring the code, how did you check to see whether you introduced new vulnerabilities?
Functionality was evaluated through test executions of the service. To re-evaluate security vulnerabilities, additional rounds of the dependency-check tool were run and examined.

# Employers sometimes ask for examples of work that you have successfully completed to show your skills, knowledge, and experience. What might you show future employers from this assignment?
To be honest I don't think showing a project that uses a version of the Spring framework that reached end-of-life nearly a decade ago, and implements next to zero functionality is a good thing to show a prospective employer. I'm hoping to be able to have more fleshed out demonstration projects available for that. 
