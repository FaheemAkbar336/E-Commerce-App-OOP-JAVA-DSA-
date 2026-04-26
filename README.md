# 🛒 E-Commerce Java GUI Application

## 📌 Overview

This project is a **Java-based E-Commerce desktop application** built using **Java Swing**. It allows users to browse products, search items, add them to a cart, and manage purchases through an interactive graphical interface.

---

## ✨ Features

* 🔍 Product Search Functionality
* 🛍️ Add Items to Cart
* ❌ Remove Items from Cart
* ↩️ Undo Last Action
* 🧾 View Cart Items
* 🎨 User-friendly GUI with images
* 📦 Multiple product categories

---

## 🛠️ Technologies Used

* **Java** – Core programming
* **Java Swing** – GUI development
* **Data Structures Used:**

  * `LinkedList` – Cart management
  * `Stack` – Undo functionality
  * `Queue` – Order handling
  * `ArrayList` – Price & quantity tracking

---

## 🧠 How It Works

1. User opens the application
2. Products are displayed in a grid layout
3. User can:

   * Search for products
   * Select quantity
   * Add items to cart
4. Cart panel allows:

   * Removing items
   * Undoing actions
   * Proceeding to buy

---

## 🚀 How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/ecommerce-java-gui.git
   ```

2. Open the project in any Java IDE (IntelliJ, Eclipse, NetBeans)

3. Compile and run:

   ```bash
   javac ECommerceAppUP.java
   java ECommerceAppUP
   ```

---

## 📂 Project Structure

```
ECommerceAppUP.java
images/
   ├── camera.png
   ├── phone.png
   ├── laptop.png
   └── ...
```

---

## 📌 Key Components

* **CartItem Class** – Stores product, quantity, and price
* **CartAction Class** – Handles undo operations
* **Order Class** – Represents order data
* **Main GUI Frame** – Displays products and cart

---

## ⚠️ Limitations

* No database integration
* No payment gateway
* Limited filtering functionality
* Runs only as a desktop application

---

## 🔮 Future Improvements

* Add database (MySQL / Firebase)
* Implement payment system
* Enhance filtering & categories
* Add user login system
* Improve UI/UX design

---

## 📌 Conclusion

This project demonstrates the use of **Java Swing and data structures** to build a functional E-commerce system with real-world features like cart management and undo operations.
