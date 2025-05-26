# Java Servlet Unit Testing with JUnit 4, Mockito, and GitHub Copilot 🚀

## Overview

This session will guide you through the practical process of unit testing Java Servlets and Filters using **JUnit 4** and **Mockito**. This demo is designed for simplicity, keeping all code within a single project structure and manually adding necessary JAR files. A significant part of this demonstration will involve leveraging **GitHub Copilot** to efficiently generate additional test cases, showcasing its utility in enhancing test coverage and development speed. ✨

## Project Context

This project focuses on a simple web application with the following key components:

* **`BuildingServlet`**: A servlet responsible for handling operations related to buildings, specifically:
    * Listing existing buildings.
    * Creating new buildings.
* **`AuthFilter`**: A servlet filter designed to validate an authentication token before allowing access to protected resources.

All source code, including the servlets and their unit tests, will reside within the same project directory for straightforward demonstration.

---

## Technical Session Guide for the Instructor

Please follow these steps to lead an effective and engaging session on unit testing these Java components:

### Step 1: Initial Project Setup & Environment Verification 🛠️✅

* **Project Structure Introduction**: Begin by explaining the project's layout. Open the main project folder in your chosen code editor (e.g., IntelliJ IDEA, Eclipse). Point out that all Java source files (for `BuildingServlet`, `AuthFilter`, and their tests) are located within this single structure.
* **Dependency Installation (Manual JARs)**:
    * **Crucial Step**: Since we're not using a build tool like Maven or Gradle, you'll need to **manually add the JUnit 4 and Mockito JAR files** to your project's classpath.
        * **Download**: Obtain the JAR files for:
            * **JUnit 4**: `junit-4.13.2.jar` (or the latest 4.x version) and its Hamcrest dependency (`hamcrest-core-1.3.jar`).
            * **Mockito**: `mockito-core-3.12.4.jar` (or a compatible version) and its transitive dependencies (`objenesis.jar`, `byte-buddy.jar`).
            * **Servlet API**: `javax.servlet-api-3.1.0.jar` (or your project's equivalent version) if it's not already in your classpath.
            * **Hibernate (if needed for compilation of test)**: Include Hibernate Core JARs if your `BuildingServlet` directly interacts with Hibernate classes that aren't mocked out.
        * **Add to Classpath**:
            * **IntelliJ IDEA**: Go to `File` > `Project Structure` > `Modules` > `Dependencies` tab. Click the `+` button and select `JARs or directories...` to add the downloaded JARs. Ensure they are set to "Compile" or "Test" scope as appropriate.
            * **Eclipse**: Right-click on your project in the Package Explorer > `Build Path` > `Configure Build Path...` > `Libraries` tab > `Add External JARs...` to add the downloaded JARs.
    * Explain that this step is essential for your Java compiler and runtime to find the testing framework classes.
* **Verify Project Functionality**:
    * Run the main web application (e.g., by deploying to Tomcat directly from your IDE). Ensure `BuildingServlet` and `AuthFilter` are functioning as expected in a deployed environment. This establishes a baseline of the application's behavior.

---

### Step 2: Create a Simple Manual Test Case for `BuildingServlet` 🧪✍️

* **Objective**: Demonstrate the fundamentals of unit testing a servlet by manually writing a basic test for the "create building" functionality.
* **Steps**:
    1.  Within your main project folder, create a new Java class for your tests, e.g., `BuildingServletTest.java`. Make sure it's in the same package structure as `BuildingServlet` if you're organizing by packages.
    2.  **Add the following manual test case**:
        ```java
        import org.junit.Test;
        import static org.mockito.Mockito.*;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.PrintWriter;
        import java.io.StringWriter;
        import org.hibernate.Session;
        import org.hibernate.Transaction;
        import org.mockito.MockedStatic;

        // Assuming BuildingServlet, Building, HibernateUtils, and your other classes are available
        // You might need to adjust imports based on your actual project structure

        public class BuildingServletTest {

            @Test
            public void testDoPostCreateBuilding() throws Exception {
                // Arrange
                HttpServletRequest request = mock(HttpServletRequest.class);
                HttpServletResponse response = mock(HttpServletResponse.class);
                Session mockSession = mock(Session.class);
                Transaction mockTx = mock(Transaction.class);

                // Mock request parameter for building name
                when(request.getParameter("buildingName")).thenReturn("Mock Tower");

                // Mock Hibernate Session and Transaction behavior
                when(mockSession.beginTransaction()).thenReturn(mockTx);

                // Mock response writer to capture output
                StringWriter sw = new StringWriter();
                when(response.getWriter()).thenReturn(new PrintWriter(sw));

                // Mock static method call for HibernateUtils.getSession()
                try (MockedStatic<HibernateUtils> mockStatic = mockStatic(HibernateUtils.class)) {
                    mockStatic.when(HibernateUtils::getSession).thenReturn(mockSession);

                    BuildingServlet servlet = new BuildingServlet();
                    servlet.doPost(request, response);

                    // Verify that saveOrUpdate and commit were called
                    verify(mockSession).saveOrUpdate(any(Building.class)); // Assumes 'Building' is your entity class
                    verify(mockTx).commit();
                }

                // Optionally, print or assert on the captured response output
                sw.flush();
                System.out.println("doPost output: " + sw);
                // Example assertion: assertTrue(sw.toString().contains("Building created successfully"));
            }
        }
        ```
    3.  **Explain the Code**: Walk participants through the test case, highlighting:
        * **`@Test`**: JUnit annotation marking a test method.
        * **`mock()`**: Mockito method to create mock objects for interfaces/classes.
        * **`when().thenReturn()`**: Defining the behavior of mock methods.
        * **`MockedStatic<HibernateUtils>`**: Essential for mocking static methods like `HibernateUtils.getSession()`.
        * **`verify()`**: Mockito method to assert that specific methods were called on the mocks.
* **Run Test**: Execute this single `testDoPostCreateBuilding` case using your IDE's JUnit runner. **Crucially, ensure it passes (turns green)**. This confirms the basic setup for testing is correct and the manual test works.

---

### Step 3: Ask GitHub Copilot to Generate More Test Cases 🤖💡

* **Objective**: Leverage GitHub Copilot's AI capabilities to efficiently expand test coverage for `BuildingServlet` and potentially `AuthFilter`.
* **Steps**:
    1.  **Open Relevant File**: In your IDE, open the `BuildingServlet.java` file (or `AuthFilter.java` if you want tests for the filter). **Having the source code open provides Copilot with the necessary context.**
    2.  **Access Copilot Chat Panel**: Open the GitHub Copilot Chat panel within your IDE.
    3.  **Issue the Prompt**: Type and send the following precise prompt:
        ```
        /tests Generate additional valid use cases for this servlet, including appropriate mock setups. Please ensure all test scenarios are meaningful and reflect realistic behavior.
        ```
        * **Discussion Point**: Explain that the `/tests` command specifically tells Copilot to generate tests, and the rest of the prompt guides it on the type and quality of tests (valid use cases, mock setups, meaningful, realistic).
    4.  **Observe and Integrate**:
        * Review the test cases generated by Copilot. These might include:
            * For `BuildingServlet`: `doPost` with missing or invalid parameters (e.g., empty building name), `doGet` for listing buildings (empty list, non-empty list), `doPost` handling a database error.
            * For `AuthFilter` (if prompted): `doFilter` with a valid token, with an invalid token, or with a missing token.
        * Review the generated code for correctness and adherence to best practices (e.g., proper use of `Mockito.when()` and `Mockito.verify()`).
        * Copy the relevant generated test methods and paste them into your `BuildingServletTest.java` (or a separate `AuthFilterTest.java` class if you prefer).

---

### Step 4: Run All Tests and Analyze Results ▶️✅

* **Objective**: Execute the full suite of tests (manual + Copilot-generated) and interpret the outcomes.
* **Steps**:
    1.  **Run All Tests**: In your IDE, run all test cases within `BuildingServletTest.java` (and any other test classes created).
    2.  **See the Result**: Observe the test results.
        * **Green**: All tests passed. This indicates that the servlet/filter behaves as expected for all tested scenarios.
        * **Red (Failures)**: Some tests failed. This is an opportunity to:
            * **Debug**: Investigate the failing test. Is the test case itself incorrect? Or is there a bug in the servlet/filter logic that the test exposed?
            * **Refine**: Adjust the servlet/filter code or the test case until it passes.
* **Discussion Point**: Emphasize the iterative nature of testing and development. Failures are not bad; they are valuable indicators to improve code quality.

---