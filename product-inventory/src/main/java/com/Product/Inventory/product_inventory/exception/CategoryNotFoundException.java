package com.Product.Inventory.product_inventory.exception;

public class CategoryNotFoundException extends RuntimeException {
  public CategoryNotFoundException(String message)
  {
    super(message);
  }
}
