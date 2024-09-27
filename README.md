**Problem Statement:**

Create a Spring Boot application for managing a product inventory system. The application should:

1. File Operations:
   - Photo Upload: Allow users to upload product images, store them in a directory, and generate unique file names.
   - Export: Provide functionality to export the product inventory details to an Excel or CSV file.
   - Import: Implement a feature to import product details from a CSV or Excel file.
2. Functional Programming:
   - Manage the inventory using Collection Framework.
   - Use Predicate, Function, Consumer, and Supplier interfaces to apply filtering, data processing, and data retrieval logic for the inventory.
   - Utilize Lambda Expressions and Method References for operations like product filtering based on stock or category.
   - Use the Stream API to filter products by category, price range, and stock availability. Handle potential null values with the Optional class.
3. External API Interaction:
   - Implement HttpClient or FeignClient to call an external pricing API synchronously. Fetch the latest price for products and update the inventory accordingly.
   - Deserialize the response using JSON mapping.
