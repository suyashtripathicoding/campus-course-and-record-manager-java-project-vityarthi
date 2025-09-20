### Project Title: Campus Course & Records Manager (CCRM)

#### 1. Project Overview & How to Run

Campus Course & Records Manager (CCRM) is a console Java application for the management of records of students and courses of an educational institution. It is a menu-driven application that supports management of students, courses, enrollments, and grades.

The project is developed with Java SE and must illustrate major concepts like Object-Oriented Programming (OOP), strong exception handling, contemporary I/O with NIO.2, the Streams API, the Date/Time API, and design patterns.

**Prerequisites:**
* Java Development Kit (JDK) 21 or higher.
* An Integrated Development Environment (IDE) such as Eclipse for convenient project handling and running.

**How to Run:**
1. Clone the Git repository.
2. Open the project in Eclipse.
3. The main class of the project is `edu.ccrm.cli.CCRMApp`.
4. Run the application in Eclipse by right-clicking `CCRMApp.java` and choosing "Run As > Java Application".

#### 2. Java Evolution, Editions & Architecture

* **Evolution of Java:**
    * Java 1.0 (1996): First release, "Write Once, Run Anywhere".
    * Java 5 (2004): Major release with generics, enums, annotations, and autoboxing.
* Java 8 (2014): Added lambdas, the Stream API, and the Date/Time API, which are utilized in this project.
* Java 11 (2018): Added long-term support (LTS) and modularity features.
* Java 17 (2021): Latest LTS release with new features such as sealed classes.

* **Java ME vs. SE vs. EE:**
* **Java SE (Standard Edition):** The base Java platform used for developing desktop and console applications. This is a Java SE application developed and executed locally.
* **Java EE (Enterprise Edition):** A broader platform used for developing large-scale, enterprise-level applications and web services.
* **Java ME (Micro Edition):** A platform used to develop applications for mobile and embedded systems.

* **Java Architecture: JDK, JRE, JVM:**
    * **JDK (Java Development Kit):** The whole software bundle for building Java applications. The JRE and development tools are part of it.
    * **JRE (Java Runtime Environment):** Offers the required libraries and the JVM to execute Java applications.
* **JVM (Java Virtual Machine):** The part of the system that runs Java bytecode. It serves as a platform between the operating system and the Java application so that it can be made platform-independent.

#### 3. Setup & Eclipse Configuration

* **Windows Java Install:**
    1. Download the JDK installer and execute it.
    2. Define the `JAVA_HOME` and `Path` environment variables.
3.  Check the installation by opening a command prompt and typing `java -version`.
    * *[Insert Screenshot of `java -version` output here]*

* **Eclipse Project Setup:**
    1.  Open Eclipse and create a new Java project.
    2.  Type "CampusCourseRecordsManager" as the project name and choose your installed JDK as the JRE.
3.  Develop the necessary packages under the `src` directory (e.g., `edu.ccrm.domain`, `edu.ccrm.service`, etc.).
    * *[Embed Screenshots of Eclipse project creation and package organization here]*

#### 4. Project Concepts Mapping

The following table maps the obligatory syllabus subjects to their implementation in the project's code.

| Syllabus Topic | File(s) / Class(es) / Method(s) |
|---|---|
| **OOP Pillars** | |
| Encapsulation | `Person.java`, `Student.java`, `Course.java` (private fields with public getters/setters) |
| Inheritance & Abstraction | `Person.java` (abstract class), `Student.java` & `Instructor.java` (extends `Person`) |
| Polymorphism | `getRole()` method override in `Student.java` & `Instructor.java` |
| **Advanced Concepts** |
| Singleton Pattern | `AppConfig.java` (private constructor, static `getInstance()` method) |
| Builder Pattern | `Transcript.java` (static nested `Builder` class, private constructor) |
| Checked & Unchecked Exceptions | `EnrollmentService.java` (throws `DuplicateEnrollmentException`, `MaxCreditLimitExceededException`) |
| Custom Exceptions | `DuplicateEnrollmentException.java`, `MaxCreditLimitExceededException.java` |
| NIO.2 & Streams | `ImportExportService.java`, `BackupService.java` (utilizing `Path`, `Files`, `Stream`) |
| Recursion | `BackupService.java` (`listFilesByDepth` method utilizing `Files.walk()`) |
| Enums with Constructors | `Grade.java` (constants with grade points)

#### 5. How to Enable Assertions & Sample Commands

Assertions are utilized for program's internal self-checks. They are turned off by default.
To execute the program with assertions turned on, use the `-ea` switch when running from the command line:

`java -ea -cp bin edu.ccrm.cli.CCRMApp`


#### 6. Author
**Patkar Atharva Satish**  
B.Tech Computer Science and Engineering (Cyber Security & Digital Forensics) 
VIT Bhopal University  
Registration No: 23BCY10221

**Under the guidance of:**  
Dr. MOHD. ISHRAT, VIT Bhopal University
