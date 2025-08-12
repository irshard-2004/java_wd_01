import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
//javac src/CurrencyConverter.java
//java -cp src CurrencyConverter
public class CurrencyConverter extends JFrame {

    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;

    private Map<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255)); // Light blue background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        exchangeRates = new LinkedHashMap<>();
        exchangeRates.put("USD - United States Dollar", 1.0);
        exchangeRates.put("EUR - Euro", 0.92);
        exchangeRates.put("INR - Indian Rupee", 83.0);
        exchangeRates.put("GBP - British Pound", 0.79);
        exchangeRates.put("JPY - Japanese Yen", 146.5);
        exchangeRates.put("AUD - Australian Dollar", 1.53);
        exchangeRates.put("CAD - Canadian Dollar", 1.36);
        exchangeRates.put("CHF - Swiss Franc", 0.88);
        exchangeRates.put("CNY - Chinese Yuan", 7.18);
        exchangeRates.put("SGD - Singapore Dollar", 1.34);
        exchangeRates.put("NZD - New Zealand Dollar", 1.67);
        exchangeRates.put("ZAR - South African Rand", 18.6);
        exchangeRates.put("SEK - Swedish Krona", 10.6);
        exchangeRates.put("NOK - Norwegian Krone", 10.5);
        exchangeRates.put("MXN - Mexican Peso", 17.1);
        exchangeRates.put("BRL - Brazilian Real", 4.9);
        exchangeRates.put("HKD - Hong Kong Dollar", 7.82);
        exchangeRates.put("THB - Thai Baht", 35.3);
        exchangeRates.put("KRW - South Korean Won", 1330.0);
        exchangeRates.put("AED - UAE Dirham", 3.67);
        exchangeRates.put("SAR - Saudi Riyal", 3.75);
        exchangeRates.put("TRY - Turkish Lira", 27.2);
        exchangeRates.put("RUB - Russian Ruble", 96.0);
        exchangeRates.put("EGP - Egyptian Pound", 31.0);
        exchangeRates.put("PKR - Pakistani Rupee", 290.0);
        exchangeRates.put("BDT - Bangladeshi Taka", 109.5);
        exchangeRates.put("VND - Vietnamese Dong", 24300.0);
        exchangeRates.put("PHP - Philippine Peso", 56.5);
        exchangeRates.put("ILS - Israeli Shekel", 3.78);
        exchangeRates.put("KWD - Kuwaiti Dinar", 0.31);
        exchangeRates.put("BHD - Bahraini Dinar", 0.38);
        exchangeRates.put("OMR - Omani Rial", 0.39);
        exchangeRates.put("QAR - Qatari Riyal", 3.64);
        exchangeRates.put("PLN - Polish Zloty", 4.1);
        exchangeRates.put("CZK - Czech Koruna", 22.4);
        exchangeRates.put("HUF - Hungarian Forint", 350.0);
        exchangeRates.put("DKK - Danish Krone", 6.85);
        exchangeRates.put("MYR - Malaysian Ringgit", 4.65);
        exchangeRates.put("IDR - Indonesian Rupiah", 15300.0);
        exchangeRates.put("ARS - Argentine Peso", 350.0);
        exchangeRates.put("CLP - Chilean Peso", 890.0);
        exchangeRates.put("COP - Colombian Peso", 4000.0);
        exchangeRates.put("NGN - Nigerian Naira", 780.0);
        exchangeRates.put("KES - Kenyan Shilling", 145.0);
        exchangeRates.put("GHS - Ghanaian Cedi", 11.5);
        exchangeRates.put("MAD - Moroccan Dirham", 10.1);
        exchangeRates.put("TWD - Taiwan Dollar", 31.8);
        exchangeRates.put("LKR - Sri Lankan Rupee", 320.0);

        JLabel title = new JLabel(" Currency Converter", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("From:"), gbc);
        gbc.gridx = 1;
        fromCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        panel.add(fromCurrency, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("To:"), gbc);
        gbc.gridx = 1;
        toCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        panel.add(toCurrency, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        amountField = new JTextField();
        panel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton convertButton = new JButton("Convert");
        convertButton.setBackground(new Color(0, 120, 215));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        panel.add(convertButton, gbc);

        gbc.gridy++;
        resultLabel = new JLabel("Converted Amount: ", SwingConstants.CENTER);
        panel.add(resultLabel, gbc);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        add(panel);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double usdAmount = amount / exchangeRates.get(from);
            double convertedAmount = usdAmount * exchangeRates.get(to);

            resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, to.substring(0, 3)));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverter().setVisible(true);
        });
    }
}
