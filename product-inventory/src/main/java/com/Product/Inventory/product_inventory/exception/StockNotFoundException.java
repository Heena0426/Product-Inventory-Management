package com.Product.Inventory.product_inventory.exception;

public class StockNotFoundException extends RuntimeException {
  public StockNotFoundException(String message)
  {
    super(message);
  }
}
