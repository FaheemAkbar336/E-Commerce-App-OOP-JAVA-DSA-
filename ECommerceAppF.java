
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class ECommerceAppF {

    // Data Structures
    private static LinkedList<CartItem> cartList = new LinkedList<>();
    private static Stack<CartAction> actionHistoryStack = new Stack<>();
    private static Queue<Order> orderQueue = new LinkedList<>();

    // GUI-related variables
    private static DefaultListModel<String> cartModel = new DefaultListModel<>();
    private static ArrayList<Double> cartPrices = new ArrayList<>();
    private static ArrayList<Integer> cartQuantities = new ArrayList<>();

    private static String[] products = {
            "Camera", "Phone", "iPad Pro", "PC & Laptop", "Split Hoodie",
            "Footwear", "Kitchen Utensils", "Watch", "PlayStation VR", "Fashion Hat",
            "Gaming Chair", "Headphones", "Smartwatch", "Tablet", "Sunglasses",
            "Earbuds", "Electric Kettle", "Bluetooth Speaker", "Drone", "Keyboard"
    };

    private static double[] prices = {
            150.00, 799.99, 999.99, 1200.00, 49.99,
            59.99, 20.00, 199.99, 499.99, 25.00,
            250.00, 89.99, 299.99, 499.00, 99.99,
            79.99, 45.99, 149.99, 899.99, 69.99
    };

    private static String[] imagePaths = {
            "images/camera.png", "images/phone.png", "images/ipad.png", "images/laptop.png",
            "images/hoodie.png", "images/footwear.png", "images/kitchen.png", "images/watch.png",
            "images/vr.png", "images/hat.png", "images/gaming_chair.png", "images/headphones.png",
            "images/smartwatch.png", "images/tablet.png", "images/sunglasses.png", "images/earbuds.png",
            "images/kettle.png", "images/speaker.png", "images/drone.png", "images/keyboard.png"
    };

    public static void main(String[] args) {
        showMainApp();
    }

    private static void showMainApp() {
        JFrame frame = new JFrame("E-Commerce Website");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLayout(new BorderLayout());

        // Header with Orange Background
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(255, 165, 0)); // Orange color
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));

        JLabel titleLabel = new JLabel("Daraz-like E-Commerce", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setOpaque(false);

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
        searchButton.setForeground(Color.BLACK);
        searchButton.addActionListener(e -> filterProducts(searchField.getText()));

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(searchPanel, BorderLayout.CENTER);

        frame.add(headerPanel, BorderLayout.NORTH);

        // Left Panel: Filters
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(10, 1, 5, 5));
        filterPanel.setBackground(Color.LIGHT_GRAY);

        JLabel filterLabel = new JLabel("Filters", JLabel.CENTER);
        filterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        filterPanel.add(filterLabel);

        JCheckBox category1 = new JCheckBox("Electronics");
        JCheckBox category2 = new JCheckBox("Fashion");
        JCheckBox category3 = new JCheckBox("Home & Kitchen");
        JCheckBox category4 = new JCheckBox("Gaming");

        filterPanel.add(category1);
        filterPanel.add(category2);
        filterPanel.add(category3);
        filterPanel.add(category4);

        JButton applyFiltersButton = new JButton("Apply Filters");
        applyFiltersButton.setBackground(new Color(255, 140, 0)); // Orange
        applyFiltersButton.setForeground(Color.WHITE);
        applyFiltersButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Filter functionality not implemented yet.");
        });
        filterPanel.add(applyFiltersButton);

        frame.add(filterPanel, BorderLayout.WEST);

        // Center Panel: Product Grid
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 4, 10, 10));
        JScrollPane productScrollPane = new JScrollPane(productPanel);
        productScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(productScrollPane, BorderLayout.CENTER);

        // Product Display Cards
        for (int i = 0; i < products.length; i++) {
            String product = products[i];
            double price = prices[i];

            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Load image for product
            ImageIcon productImage = new ImageIcon(imagePaths[i]);
            Image scaledImage = productImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

            JLabel nameLabel = new JLabel("<html>" + product + "<br>Price: $" + price + "</html>", JLabel.CENTER);

            JComboBox<Integer> quantityDropdown = new JComboBox<>();
            for (int j = 1; j <= 10; j++) {
                quantityDropdown.addItem(j);
            }

            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.setBackground(Color.BLUE);
            addToCartButton.setForeground(Color.WHITE);
            addToCartButton.addActionListener(e -> {
                int quantity = (int) quantityDropdown.getSelectedItem();
                double totalItemPrice = price * quantity;
                String cartEntry = product + " (x" + quantity + ") - $" + totalItemPrice;

                // Adding the item to the linked list cart and action history
                cartList.add(new CartItem(product, quantity, totalItemPrice));
                actionHistoryStack.push(new CartAction("add", product, quantity));

                cartModel.addElement(cartEntry);
                cartPrices.add(totalItemPrice);
                cartQuantities.add(quantity);

                JOptionPane.showMessageDialog(frame, "Added to cart: " + cartEntry);
            });

            JPanel actionPanel = new JPanel();
            actionPanel.setLayout(new GridLayout(1, 2, 5, 5));
            actionPanel.add(quantityDropdown);
            actionPanel.add(addToCartButton);

            itemPanel.add(imageLabel, BorderLayout.NORTH);
            itemPanel.add(nameLabel, BorderLayout.CENTER);
            itemPanel.add(actionPanel, BorderLayout.SOUTH);

            productPanel.add(itemPanel);
        }

        filterProducts("");

        // Right Panel: Cart Section
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());
        JLabel cartLabel = new JLabel("Shopping Cart", JLabel.CENTER);
        cartLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartPanel.add(cartLabel, BorderLayout.NORTH);

        JList<String> cartListDisplay = new JList<>(cartModel);
        cartListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane cartScrollPane = new JScrollPane(cartListDisplay);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        JPanel cartButtonsPanel = new JPanel();
        JButton removeFromCartButton = new JButton("Remove");
        removeFromCartButton.setBackground(Color.RED);
        removeFromCartButton.setForeground(Color.WHITE);
        removeFromCartButton.addActionListener(e -> {
            int selectedIndex = cartListDisplay.getSelectedIndex();
            if (selectedIndex != -1) {
                // Remove item from LinkedList and Stack
                CartItem itemToRemove = cartList.get(selectedIndex);
                cartList.remove(selectedIndex);
                actionHistoryStack.push(new CartAction("remove", itemToRemove.product, itemToRemove.quantity));

                cartModel.remove(selectedIndex);
                cartPrices.remove(selectedIndex);
                cartQuantities.remove(selectedIndex);

                JOptionPane.showMessageDialog(frame, "Item removed from the cart.");
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an item to remove.");
            }
        });

        JButton undoButton = new JButton("Undo");
        undoButton.setBackground(Color.YELLOW);
        undoButton.addActionListener(e -> {
            if (!actionHistoryStack.isEmpty()) {
                CartAction lastAction = actionHistoryStack.pop();
                if (lastAction.action.equals("add")) {
                    // Undo add to cart
                    cartList.removeLast();
                    cartModel.removeElementAt(cartModel.size() - 1);
                    JOptionPane.showMessageDialog(frame, "Last action undone.");
                } else if (lastAction.action.equals("remove")) {
                    // Undo remove from cart
                    cartList.add(new CartItem(lastAction.product, lastAction.quantity,
                            prices[findProductIndex(lastAction.product)] * lastAction.quantity));
                    cartModel.addElement(lastAction.product + " (x" + lastAction.quantity + ")");
                    JOptionPane.showMessageDialog(frame, "Last remove undone.");
                }
            }
        });

        JButton buyButton = new JButton("Buy");
        buyButton.setBackground(Color.GREEN);
        buyButton.setForeground(Color.WHITE);
        buyButton.addActionListener(e -> {
            if (cartModel.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Your cart is empty. Add items to cart before buying.");
            } else {
                new BuyItem(frame, cartModel, cartPrices); // Call the BuyItem class
            }
            // Open the BuyItem dialog for the user to provide purchase details
        });

        cartButtonsPanel.add(removeFromCartButton);
        cartButtonsPanel.add(undoButton);
        cartButtonsPanel.add(buyButton);
        cartPanel.add(cartButtonsPanel, BorderLayout.SOUTH);

        frame.add(cartPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private static void filterProducts(String query) {
        // Product display filtering logic
    }

    private static int findProductIndex(String productName) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].equals(productName)) {
                return i;
            }
        }
        return -1; // Product not found
    }

    // Cart Item Class
    static class CartItem {
        String product;
        int quantity;
        double totalPrice;

        CartItem(String product, int quantity, double totalPrice) {
            this.product = product;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }
    }

    // Cart Action Class (for Undo Stack)
    static class CartAction {
        String action;
        String product;
        int quantity;

        CartAction(String action, String product, int quantity) {
            this.action = action;
            this.product = product;
            this.quantity = quantity;
        }
    }

    // Order Class (for Queue)
    static class Order {
        LinkedList<CartItem> items;
        double totalAmount;

        Order(LinkedList<CartItem> items, double totalAmount) {
            this.items = items;
            this.totalAmount = totalAmount;
        }
    }

    // BuyItem Dialog for providing purchase details
    static class BuyItem extends JDialog {
        public BuyItem(JFrame parentFrame, DefaultListModel<String> cartModel, ArrayList<Double> cartPrices) {
            super(parentFrame, "Buy Items", true);
            setLayout(new BorderLayout());
            setSize(400, 400);

            JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

            formPanel.add(new JLabel("Name:"));
            JTextField nameField = new JTextField();
            formPanel.add(nameField);

            formPanel.add(new JLabel("Address:"));
            JTextField addressField = new JTextField();
            formPanel.add(addressField);

            formPanel.add(new JLabel("City:"));
            JTextField cityField = new JTextField();
            formPanel.add(cityField);

            formPanel.add(new JLabel("Zip Code:"));
            JTextField zipField = new JTextField();
            formPanel.add(zipField);

            formPanel.add(new JLabel("Payment Method:"));
            JComboBox<String> paymentMethodCombo = new JComboBox<>(
                    new String[] { "Credit Card", "Debit Card", "PayPal", "Cash on Delivery" });
            formPanel.add(paymentMethodCombo);

            JButton submitButton = new JButton("Submit");
            submitButton.setBackground(Color.GREEN);
            submitButton.setForeground(Color.BLACK);
            submitButton.addActionListener(e -> {
                if (nameField.getText().isEmpty() || addressField.getText().isEmpty() ||
                        cityField.getText().isEmpty() || zipField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    double totalPrice = cartPrices.stream().mapToDouble(Double::doubleValue).sum();
                    JOptionPane.showMessageDialog(this,
                            "Purchase successful!\nTotal Amount: $" + totalPrice + "\nThank you for shopping!");
                    cartModel.clear();
                    cartPrices.clear();
                    cartQuantities.clear();
                    dispose();
                }
            });
            formPanel.add(submitButton);

            add(formPanel, BorderLayout.CENTER);
            setVisible(true);
        }
    }
}
