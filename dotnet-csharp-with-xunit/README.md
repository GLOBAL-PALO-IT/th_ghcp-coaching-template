# WebApplicationWithXUnit 🛍️🧪

`WebApplicationWithXUnit` is a .NET Core MVC application designed to demonstrate a simple product management system. It showcases standard MVC patterns, Entity Framework Core for data access, and includes a companion XUnit test project to illustrate best practices in unit testing controllers and services/repositories.

This project serves as a practical example for learning and demonstrating:
* ASP.NET Core MVC fundamentals (Controllers, Views, Models, ViewModels).
* Repository pattern for data abstraction.
* Entity Framework Core for database interaction (Code-First approach with Migrations).
* Dependency Injection.
* Unit Testing with XUnit and FakeItEasy.
* AI-assisted development and testing with GitHub Copilot.

## 🌟 Features

* **Product Listing**: Displays a list of products with details like name, description, and price.
* **Basic Product Management (Conceptual)**: The structure allows for easy extension to include Create, Read, Update, Delete (CRUD) operations for products.
* **Clear Separation of Concerns**:
    * **Models**: Define the domain entities (`Product.cs`, `AppUser.cs`).
    * **Interfaces**: Define contracts for data access (`IProductRepository.cs`).
    * **Repositories**: Implement data access logic using Entity Framework Core (`ProductRepository.cs`).
    * **ViewModels**: Shape data specifically for views (`IndexProductViewModel.cs`).
    * **Controllers**: Handle user requests and orchestrate responses (`ProductController.cs`).
    * **Data**: Contains EF Core `DbContext` (`ApplicationDbContext.cs`) and database seeding logic (`Seed.cs`).
* **Unit Test Project**: A dedicated project (`WebApplicationWithXUnit.Tests` or similar) demonstrates how to unit test the application, particularly the `ProductController`, using XUnit and FakeItEasy.

## 🛠️ Tech Stack

* **.NET Core MVC** (e.g., .NET 6.0, .NET 7.0, .NET 8.0 - *Please specify your project's version*)
* **C#**
* **Entity Framework Core** (for ORM)
    * Database Provider: *(e.g., SQL Server, SQLite, PostgreSQL - Please specify)*
    * EF Core Migrations
* **XUnit**: Testing framework.
* **FakeItEasy**: Mocking library for unit tests.
* **ASP.NET Core Identity** *(If applicable, based on `AppUser.cs` and `ApplicationDbContext` setup)*
* **Frontend**: HTML, CSS, Bootstrap *(or specify other frontend technologies if used)*

## ⚙️ Getting Started

### Prerequisites

* [.NET SDK](https://dotnet.microsoft.com/download) (*Specify required version, e.g., .NET 8.0 SDK or newer*)
* A C# compatible IDE (e.g., Visual Studio 2022, VS Code with C# Dev Kit)
* Database Server: *(e.g., SQL Server Express, PostgreSQL server - Specify if not using a local file-based DB like SQLite)*
* (Optional) Database management tool (e.g., SQL Server Management Studio, Azure Data Studio, DBeaver)
* (Optional, for AI-assisted features) GitHub Copilot extension in your IDE.

### Setup & Installation

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd WebApplicationWithXUnit
    ```

2.  **Restore NuGet packages:**
    Open the `WebApplicationWithXUnit.sln` solution file in Visual Studio. NuGet packages should restore automatically. Alternatively, run from the solution's root directory:
    ```bash
    dotnet restore
    ```

3.  **Database Configuration:**
    * The primary database connection string is located in `WebApplicationWithXUnit/appsettings.json`. Development-specific overrides might be in `WebApplicationWithXUnit/appsettings.Development.json`.
    * Update the `DefaultConnection` (or your specific connection string name) to point to your database instance.
      Example for SQL Server LocalDB:
      ```json
      "ConnectionStrings": {
        "DefaultConnection": "Server=(localdb)\\mssqllocaldb;Database=WebAppWithXUnitDB_Demo;Trusted_Connection=True;MultipleActiveResultSets=true"
      }
      ```
      *(Adjust the `Database` name or other parameters as needed for your environment.)*

4.  **Apply Entity Framework Core Migrations:**
    This step creates the database schema based on your `DbContext` and entity configurations.
    * **Using Package Manager Console (in Visual Studio):**
        1.  Go to `View` > `Other Windows` > `Package Manager Console`.
        2.  Ensure the `WebApplicationWithXUnit` project is selected as the "Default project".
        3.  Run:
            ```powershell
            Update-Database
            ```
    * **Using .NET CLI:**
        1.  Navigate to the `WebApplicationWithXUnit` project directory (`cd WebApplicationWithXUnit`).
        2.  Run:
            ```bash
            dotnet ef database update
            ```
    *(If you are setting up the database for the first time and no migrations exist, you might need to create an initial migration first: `dotnet ef migrations add InitialCreate` followed by `dotnet ef database update`.)*

5.  **(Optional) Seed Initial Data:**
    The `Data/Seed.cs` class (if implemented and called from `Program.cs`) can populate the database with initial data. This may run automatically during application startup or after migrations if configured. Verify its setup in `Program.cs`.

### Running the Application

* **Using Visual Studio:**
    1.  Ensure `WebApplicationWithXUnit` is set as the startup project in the solution.
    2.  Press `F5` or click the "Start Debugging" button (often a green play icon).
* **Using .NET CLI:**
    1.  Navigate to the `WebApplicationWithXUnit` project directory.
    2.  Run:
        ```bash
        dotnet run
        ```
    The application will typically be accessible at `https://localhost:XXXX` or `http://localhost:YYYY`. Check the console output from `dotnet run` for the exact URLs.

## 🧪 Running Tests

The solution includes a unit test project (e.g., `WebApplicationWithXUnit.Tests`).

* **Using Visual Studio Test Explorer:**
    1.  Open the Test Explorer window by going to `Test` > `Test Explorer`.
    2.  Click the "Run All Tests" icon, or right-click on specific tests/fixtures to run them.
* **Using .NET CLI:**
    1.  Navigate to the solution's root directory (containing `WebApplicationWithXUnit.sln`) or directly into the test project directory.
    2.  Run:
        ```bash
        dotnet test
        ```
    This command will discover and execute all tests in the solution or specified test project.
