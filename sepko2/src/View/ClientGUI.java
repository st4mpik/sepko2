package View;
/**
* @author Adam Minarik, Leonard Merva, Marek Dvoracek, Denis Drga, Marius Ungurean
*/
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Domain.Mediator.ModelManager;
import Domain.Mediator.RmiClient;
import Domain.Mediator.RmiServerInterface;
import Domain.Model.Item;
import Domain.Model.Order;

public class ClientGUI extends JFrame {

	private JMenuBar topMenuBar;
	private JMenu topMenu;
	private JMenuItem menuItemEditMenu;
	private JTabbedPane menuTabs;
	private ModelManager manager;
	private double totalPrice;
	private RmiServerInterface remoteService;
	private final int ID;
	private RmiClient client;

	final ImageIcon imageIcon = new ImageIcon("logo.png");
	
	// JPANELS //
	// *********************************************************************
	private JPanel mainPanel;

	private JPanel eastPanel;
	private JPanel westPanel;

	private JPanel eastButtonPanel;
	private JPanel eastListPanel;
	private JPanel westButtonPanel;
	private JPanel westListPanel;
	private JPanel centerButtonPanel;
	private JPanel centerPanel;
	private JPanel plusButtonPanel;
	private JPanel priceMiddlePanel;

	private JPanel starterPanel;
	private JPanel soupPanel;
	private JPanel porkPanel;
	private JPanel beefPanel;
	private JPanel chickenPanel;
	private JPanel pastaPanel;
	private JPanel seaFood;
	private JPanel sideDish;
	private JPanel desert;
	private JPanel nonAlcoholicDrinks;
	private JPanel alcoholicDrinks;
	private JPanel infoPanel;
	private JPanel minusButtonPanel;

	// JLABELS //
	// *********************************************************************

	private JLabel price;

	// JBUTTONS //
	// *********************************************************************

	private JButton plusButton;
	private JButton orderButton;
	private JButton minusButton;
	private JButton callStaffButton;
	private JButton moreInfo;

	// JLISTS & DEFAULT LIST MODELS //
	// *********************************************************************

	private JList<Item> starterList;
	private JList<Item> soupList;
	private JList<Item> porkList;
	private JList<Item> beefList;
	private JList<Item> chickenList;
	private JList<Item> pastaList;
	private JList<Item> seaFoodList;
	private JList<Item> sideDishList;
	private JList<Item> dessertList;
	private JList<Item> nonAlcoholicDrinksList;
	private JList<Item> alcoholicDrinksList;
	
	private DefaultListModel<Item> starterModel;
	private DefaultListModel<Item> soupModel;
	private DefaultListModel<Item> porkModel;
	private DefaultListModel<Item> beefModel;
	private DefaultListModel<Item> chickenModel;
	private DefaultListModel<Item> pastaModel;
	private DefaultListModel<Item> seaFoodModel;
	private DefaultListModel<Item> sideDishModel;
	private DefaultListModel<Item> dessertModel;
	private DefaultListModel<Item> nonAlcoholicDrinksModel;
	private DefaultListModel<Item> alcoholicDrinksModel;
	
	private DefaultListModel<Item> modelOfOrders = new DefaultListModel<Item>();

	  
	@SuppressWarnings("unchecked")
	private JList<Item> listOfOrder = new JList(modelOfOrders) {
	         Image image = imageIcon.getImage();
	         Image imageTemp = image.getScaledInstance(325, 400, Image.SCALE_DEFAULT);
	         {
	            setOpaque(false);
;
	         }
	         public void paintComponent(Graphics g) {
	            g.drawImage(imageTemp, 240, 180, this);
	            super.paintComponent(g);
	         }
	      };

	
	// JSCROLLPANES
	// *********************************************************************
	
	private JScrollPane ordersScrollPane ;
	
	private JScrollPane porkListScrollPane;
	private JScrollPane beefListScrollPane;
	private JScrollPane chickenListScrollPane;
	private JScrollPane soupListScrollPane;
	private JScrollPane seaFoodListScrollPane;
	private JScrollPane dessertListScrollPane;
	private JScrollPane sideDishListScrollPane;
	private JScrollPane starterListScrollPane;
	private JScrollPane pastaScrollPane;
	private JScrollPane nonAlcoholicScrollPane;
	private JScrollPane alcoholicScrollPane;
	

	public ClientGUI(ModelManager manager, int ID,
			RmiServerInterface remoteService, RmiClient client) throws Exception {
		
		
		this.manager = manager;
		this.ID = ID;
		this.remoteService = remoteService;
		this.client = client;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(topMenuBar);
		setTitle("SEP Restaurant System");
		setLayout(new BorderLayout());
		setComponents();
		addPanelsAndLayouts();
		addBorders();
		addActionListeners();
		plusButton.setPreferredSize(new Dimension(200, 125));
		minusButton.setPreferredSize(new Dimension(200, 125));
		pack();
	}

	private void setComponents() {
		totalPrice = 0;

		// TOP MENU BAR //
		// **********************************************************************

		topMenuBar = new JMenuBar();
		topMenu = new JMenu("Edit");
		menuItemEditMenu = new JMenuItem("Edit menu..");
		menuTabs = new JTabbedPane();

		// JPANELS //
		// **********************************************************************

		westPanel = new JPanel();
		eastPanel = new JPanel();
		eastButtonPanel = new JPanel();
		mainPanel = new JPanel();
		eastListPanel = new JPanel();
		westButtonPanel = new JPanel();
		westListPanel = new JPanel();
		centerButtonPanel = new JPanel();
		centerPanel = new JPanel();
		plusButtonPanel = new JPanel();
		priceMiddlePanel = new JPanel();
		infoPanel = new JPanel();
		starterPanel = new JPanel();
		soupPanel = new JPanel();
		porkPanel = new JPanel();
		beefPanel = new JPanel();
		chickenPanel = new JPanel();
		pastaPanel = new JPanel();
		seaFood = new JPanel();
		sideDish = new JPanel();
		desert = new JPanel();
		nonAlcoholicDrinks = new JPanel();
		alcoholicDrinks = new JPanel();
		minusButtonPanel = new JPanel();

		// JBUTTONS //
		// *********************************************************************

		minusButton = new JButton("-");
		minusButton.setFont(new Font("Arial", Font.BOLD, 25));
		plusButton = new JButton("+");
		plusButton.setFont(new Font("Arial", Font.BOLD, 19));
		orderButton = new JButton("Order");
		callStaffButton = new JButton("Call saff");
		moreInfo = new JButton("More Info");

		// JLISTS & DEFAULT LIST MODELS

		starterModel = new DefaultListModel<Item>();
		soupModel = new DefaultListModel<Item>();
		porkModel = new DefaultListModel<Item>();
		beefModel = new DefaultListModel<Item>();
		chickenModel = new DefaultListModel<Item>();
		pastaModel = new DefaultListModel<Item>();
		seaFoodModel = new DefaultListModel<Item>();
		sideDishModel = new DefaultListModel<Item>();
		dessertModel = new DefaultListModel<Item>();
		nonAlcoholicDrinksModel = new DefaultListModel<Item>();
		alcoholicDrinksModel = new DefaultListModel<Item>();

		

		starterList = new JList(starterModel);
		starterList.setFont(new Font("Arial", Font.BOLD , 19));
		soupList = new JList(soupModel);
		soupList.setFont(new Font("Arial", Font.BOLD , 19));
		porkList = new JList(porkModel);
		porkList.setFont(new Font("Arial", Font.BOLD , 19));
		beefList = new JList(beefModel);
		beefList.setFont(new Font("Arial", Font.BOLD , 19));
		chickenList = new JList(chickenModel);
		chickenList.setFont(new Font("Arial", Font.BOLD , 19));
		pastaList = new JList(pastaModel);
		pastaList.setFont(new Font("Arial", Font.BOLD , 19));
		seaFoodList = new JList(seaFoodModel);
		seaFoodList.setFont(new Font("Arial", Font.BOLD , 19));
		sideDishList = new JList(sideDishModel);
		sideDishList.setFont(new Font("Arial", Font.BOLD , 19));
		dessertList = new JList(dessertModel);
		dessertList.setFont(new Font("Arial", Font.BOLD , 19));
		nonAlcoholicDrinksList = new JList(nonAlcoholicDrinksModel);
		nonAlcoholicDrinksList.setFont(new Font("Arial", Font.BOLD , 19));
		alcoholicDrinksList = new JList(alcoholicDrinksModel);
		alcoholicDrinksList.setFont(new Font("Arial", Font.BOLD, 19));

		listOfOrder.setFont(new Font("Arial", Font.BOLD, 19));
		listOfOrder.setForeground(Color.BLACK);

		

		// JSCROLLPANES
		// *********************************************************************
		
		
		ordersScrollPane = new JScrollPane(listOfOrder);
		porkListScrollPane = new JScrollPane(porkList);
		beefListScrollPane = new JScrollPane(beefList);
		chickenListScrollPane = new JScrollPane(chickenList);
		soupListScrollPane = new JScrollPane(soupList);
		seaFoodListScrollPane = new JScrollPane(seaFoodList);
		sideDishListScrollPane = new JScrollPane(sideDishList);
		dessertListScrollPane = new JScrollPane(dessertList);
		starterListScrollPane = new JScrollPane(starterList);
		pastaScrollPane = new JScrollPane(pastaList);
		nonAlcoholicScrollPane = new JScrollPane(nonAlcoholicDrinksList);
		alcoholicScrollPane = new JScrollPane(alcoholicDrinksList);
		
		listOfOrder.setCellRenderer(new OpaqueCellRenderer());
		// JLABELS //
		// **********************************************************************

		price = new JLabel("Total price: 0 Kr. ");
		price.setFont(new Font("Arial", Font.BOLD, 16));
	}

	private void addBorders() {
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		eastPanel.setBorder(BorderFactory.createTitledBorder("ORDER ITEMS"));
		menuTabs.setBorder(BorderFactory.createTitledBorder("MENU"));
		minusButtonPanel.setBorder(BorderFactory
				.createEmptyBorder(100, 0, 0, 0));
		priceMiddlePanel.setBorder(BorderFactory
				.createEmptyBorder(100, 0, 0, 0));
	}

	private void addPanelsAndLayouts() {

		// SETTING OF LAYOUTS //
		// ***********************************************************

		mainPanel.setLayout(new GridLayout(1, 2));
		eastPanel.setLayout(new BorderLayout());
		westPanel.setLayout(new BorderLayout());
		centerPanel.setLayout(new BorderLayout());

		starterPanel.setLayout(new BorderLayout());
		soupPanel.setLayout(new BorderLayout());
		porkPanel.setLayout(new BorderLayout());
		beefPanel.setLayout(new BorderLayout());
		chickenPanel.setLayout(new BorderLayout());
		pastaPanel.setLayout(new BorderLayout());
		seaFood.setLayout(new BorderLayout());
		sideDish.setLayout(new BorderLayout());
		desert.setLayout(new BorderLayout());
		nonAlcoholicDrinks.setLayout(new BorderLayout());
		alcoholicDrinks.setLayout(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		centerButtonPanel.setLayout(new GridBagLayout());
		eastButtonPanel.setLayout(new GridLayout(1, 6));
		eastListPanel.setLayout(new BorderLayout());
		westButtonPanel.setLayout(new GridLayout(1, 1));
		westListPanel.setLayout(new BorderLayout());

		// ADDING INTO PANELS //
		// *********************************************************************
		
		
		menuTabs.add("Starter", starterPanel);
		menuTabs.add("Soup", soupPanel);
		menuTabs.add("Pork", porkPanel);
		menuTabs.add("Beef", beefPanel);
		menuTabs.add("Chicken", chickenPanel);
		menuTabs.add("Pasta", pastaPanel);
		menuTabs.add("Sea Food", seaFood);
		menuTabs.add("Side dish", sideDish);
		menuTabs.add("Desserts", desert);
		menuTabs.add("Non-alcoholic drinks", nonAlcoholicDrinks);
		menuTabs.add("Alcoholic drinks", alcoholicDrinks);
		
		starterPanel.add(starterListScrollPane, BorderLayout.CENTER);
		soupPanel.add(soupListScrollPane, BorderLayout.CENTER);
		porkPanel.add(porkListScrollPane, BorderLayout.CENTER);
		beefPanel.add(beefListScrollPane, BorderLayout.CENTER);
		chickenPanel.add(chickenListScrollPane, BorderLayout.CENTER);
		pastaPanel.add(pastaScrollPane, BorderLayout.CENTER);
		seaFood.add(seaFoodListScrollPane, BorderLayout.CENTER);
		sideDish.add(sideDishListScrollPane, BorderLayout.CENTER);
		desert.add(dessertListScrollPane, BorderLayout.CENTER);
		nonAlcoholicDrinks.add(nonAlcoholicScrollPane, BorderLayout.CENTER);
		alcoholicDrinks.add(alcoholicScrollPane, BorderLayout.CENTER);
	

		mainPanel.add(westPanel);
		mainPanel.add(eastPanel);
		eastListPanel.add(ordersScrollPane);
		westListPanel.add(menuTabs);
		eastButtonPanel.add(orderButton);
		eastButtonPanel.add(callStaffButton);
		minusButtonPanel.add(minusButton);
		priceMiddlePanel.add(price);
		plusButtonPanel.add(plusButton);
		infoPanel.add(moreInfo);

		centerButtonPanel.add(plusButtonPanel, gbc);
		centerButtonPanel.add(priceMiddlePanel, gbc);
		centerButtonPanel.add(minusButtonPanel, gbc);
		centerButtonPanel.add(infoPanel,gbc);

		eastPanel.add(eastButtonPanel, BorderLayout.SOUTH);
		eastPanel.add(eastListPanel, BorderLayout.CENTER);
		westPanel.add(westButtonPanel, BorderLayout.SOUTH);
		westPanel.add(westListPanel, BorderLayout.CENTER);
		westPanel.add(centerButtonPanel, BorderLayout.EAST);
		mainPanel.add(westPanel);
		mainPanel.add(eastPanel);
		add(mainPanel, BorderLayout.CENTER);
		topMenuBar.add(topMenu);
		topMenu.add(menuItemEditMenu);
	}

	// ADDING FUNCTIONS //
	// *********************************************************************
	public void getMenuByType() {
		pastaModel.clear();
		for (int i = 0; i < manager.getMenuByType("pasta").size(); i++) {
			pastaModel.addElement(manager.getMenuByType("pasta")
					.get(i));
		}

		porkModel.clear();
		for (int i = 0; i < manager.getMenuByType("pork").size(); i++) {
			porkModel.addElement(manager.getMenuByType("pork").get(
					i));
		}

		chickenModel.clear();
		for (int i = 0; i < manager.getMenuByType("chicken").size(); i++) {
			chickenModel.addElement(manager
					.getMenuByType("chicken").get(i));
		}
		starterModel.clear();
		for (int i = 0; i < manager.getMenuByType("starter").size(); i++) {
			starterModel.addElement(manager
					.getMenuByType("starter").get(i));
		}
		

		beefModel.clear();
		for (int i = 0; i < manager.getMenuByType("beef").size(); i++) {
			beefModel.addElement(manager.getMenuByType("beef").get(
					i));
		}

		dessertModel.clear();
		for (int i = 0; i < manager.getMenuByType("dessert").size(); i++) {
			dessertModel.addElement(manager
					.getMenuByType("dessert").get(i));
		}

		soupModel.clear();
		for (int i = 0; i < manager.getMenuByType("soups").size(); i++) {
			soupModel.addElement(manager.getMenuByType("soups")
					.get(i));
		}

		seaFoodModel.clear();
		for (int i = 0; i < manager.getMenuByType("seafood").size(); i++) {
			seaFoodModel.addElement(manager
					.getMenuByType("seafood").get(i));
		}
		

		sideDishModel.clear();
		for (int i = 0; i < manager.getMenuByType("sidedish")
				.size(); i++) {
			sideDishModel.addElement(manager.getMenuByType(
					"sidedish").get(i));
		}

		alcoholicDrinksModel.clear();
		for (int i = 0; i < manager.getMenuByType("alcoholic")
				.size(); i++) {
			alcoholicDrinksModel.addElement(manager.getMenuByType(
					"alcoholic").get(i));
		}

		nonAlcoholicDrinksModel.clear();
		for (int i = 0; i < manager.getMenuByType("nonalcoholic")
				.size(); i++) {
			nonAlcoholicDrinksModel.addElement(manager
					.getMenuByType("nonalcoholic").get(i));
		}

}

	class MenuByType implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {

			JTabbedPane sourceTabbedPane = (JTabbedPane) arg0.getSource();
			int index = sourceTabbedPane.getSelectedIndex();
			String temp = sourceTabbedPane.getTitleAt(index).toLowerCase();
		

			switch (temp) {
			case "pasta":
				pastaModel.clear();
				for (int i = 0; i < manager.getMenuByType("pasta").size(); i++) {
					pastaModel.addElement(manager.getMenuByType("pasta")
							.get(i));
				}
				break;

			case "pork":
				porkModel.clear();
				for (int i = 0; i < manager.getMenuByType("pork").size(); i++) {
					porkModel.addElement(manager.getMenuByType("pork").get(
							i));
				}
				break;

			case "chicken":
				chickenModel.clear();
				for (int i = 0; i < manager.getMenuByType("chicken").size(); i++) {
					chickenModel.addElement(manager
							.getMenuByType("chicken").get(i));
				}
				break;

			case "starter":
				starterModel.clear();
				for (int i = 0; i < manager.getMenuByType("starter").size(); i++) {
					starterModel.addElement(manager
							.getMenuByType("starter").get(i));
				}
				break;

			case "beef":
				beefModel.clear();
				for (int i = 0; i < manager.getMenuByType("beef").size(); i++) {
					beefModel.addElement(manager.getMenuByType("beef").get(
							i));
				}
				break;

			case "desserts":
				dessertModel.clear();
				for (int i = 0; i < manager.getMenuByType("dessert").size(); i++) {
					dessertModel.addElement(manager
							.getMenuByType("dessert").get(i));
				}
				break;

			case "soup":
				soupModel.clear();
				for (int i = 0; i < manager.getMenuByType("soups").size(); i++) {
					soupModel.addElement(manager.getMenuByType("soups")
							.get(i));
				}
				break;

			case "sea food":
				seaFoodModel.clear();
				for (int i = 0; i < manager.getMenuByType("seafood").size(); i++) {
					seaFoodModel.addElement(manager
							.getMenuByType("seafood").get(i));
				}
				break;

			case "side dish":
				sideDishModel.clear();
				for (int i = 0; i < manager.getMenuByType("sidedish")
						.size(); i++) {
					sideDishModel.addElement(manager.getMenuByType(
							"sidedish").get(i));
				}
				break;

			case "alcoholic drinks":
				alcoholicDrinksModel.clear();
				for (int i = 0; i < manager.getMenuByType("alcoholic")
						.size(); i++) {
					alcoholicDrinksModel.addElement(manager.getMenuByType(
							"alcoholic").get(i));
				}
				break;

			case "non-alcoholic drinks":
				nonAlcoholicDrinksModel.clear();
				for (int i = 0; i < manager.getMenuByType("nonalcoholic")
						.size(); i++) {
					nonAlcoholicDrinksModel.addElement(manager
							.getMenuByType("nonalcoholic").get(i));
				}
				break;

			default:
				JOptionPane.showMessageDialog(ClientGUI.this,
						"Menu tab not initialized. Call staff!");
			}
		}
	}

	class plusItemToOrder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel tab = (JPanel) menuTabs.getSelectedComponent();
			JScrollPane ScrollPane = (JScrollPane) tab.getComponent(0);
			JViewport viewport = (JViewport) ScrollPane.getComponent(0);
			JList list = (JList) viewport.getComponent(0);
			try {
				Item selectedElement = (Item) list.getSelectedValue();
				modelOfOrders.addElement(selectedElement);
				incrementPrice(selectedElement);
			} catch (NullPointerException exception) {
				JOptionPane.showMessageDialog(ClientGUI.this,
						"Please select the item from menu first");
			}
		}

	}

	class minusItemFromOrder implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Item selectedElement = (Item) listOfOrder.getSelectedValue();
				modelOfOrders.removeElement(selectedElement);
				decrementPrice(selectedElement);
			} catch (NullPointerException exception) {
				JOptionPane.showMessageDialog(ClientGUI.this,
						"Please select the item you want to remove first ");
			}
		}
	}

	class createOrder implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Order order = new Order();
			if (modelOfOrders.size() == 0) {
				JOptionPane.showMessageDialog(ClientGUI.this,
						"Please add items you want to order first \n"
								+ "or call staff");
			} else {
				for (int i = 0; i < modelOfOrders.size(); i++) {
					order.addItem(modelOfOrders.getElementAt(i));
				}
				modelOfOrders.clear();
				client.setOrder(order);
				try {
					remoteService.doCallbacks(ID);
					remoteService.newOrderOnTable(ID);
					remoteService.updateKitchen(order);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(ClientGUI.this,
						"Order successful! \n" + "Total price: " + totalPrice
								+ " Kr.");
				totalPrice = 0;
				price.setText("Total price: " + totalPrice + " Kr.");
			}
		}
	}

	class callStaff implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				remoteService.callStaff(ID);
				JOptionPane
						.showMessageDialog(ClientGUI.this, "Staff has seen your request");
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(ClientGUI.this,
						"Oops, something went wrong");
			}
		}
	}
	
	class moreInfo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel tab = (JPanel) menuTabs.getSelectedComponent();
			JScrollPane ScrollPane = (JScrollPane) tab.getComponent(0);
			JViewport viewport = (JViewport) ScrollPane.getComponent(0);
			JList list = (JList) viewport.getComponent(0);
			try{
			Item selectedElement = (Item) list.getSelectedValue();
			JOptionPane.showMessageDialog(ClientGUI.this,
						selectedElement.moreInfo());
			}
			catch(NullPointerException m){
				JOptionPane.showMessageDialog(ClientGUI.this,
						"Please select the item from menu first");
			}
		}
	}
	
	public void incrementPrice(Item item) {
		totalPrice += item.getPrice();
		price.setText("Total price: " + totalPrice + " Kr.");
	}

	public void decrementPrice(Item item) {
		totalPrice -= item.getPrice();
		price.setText("Total price: " + totalPrice + " Kr.");
	}

	public void addActionListeners() {

		menuTabs.addChangeListener(new MenuByType());
		plusButton.addActionListener(new plusItemToOrder());
		minusButton.addActionListener(new minusItemFromOrder());
		orderButton.addActionListener(new createOrder());
		callStaffButton.addActionListener(new callStaff());
		moreInfo.addActionListener(new moreInfo());

		// Filling up pork on start because its first selected tab

		for (int i = 0; i < manager.getMenuByType("starter").size(); i++) {
			starterModel
					.addElement(manager.getMenuByType("starter").get(i));
		}
	}
	
	public void setManager(ModelManager manager) {
		this.manager = manager;
	}
	
	
	class OpaqueCellRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {
		   public Component getListCellRendererComponent(
		      JList list,
		      Object value,            // value to display
		      int index,               // cell index
		      boolean isSelected,      // is the cell selected
		      boolean cellHasFocus)    // the list and the cell have the focus
		   {
		      Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		  
		      if (isSelected) {
		         ((JComponent) c).setOpaque(true);
		         c.setBackground(list.getSelectionBackground());
		         c.setForeground(list.getSelectionForeground());
		      }
		      else {
		         ((JComponent) c).setOpaque(false);
		         c.setBackground(list.getBackground());
		         c.setForeground(list.getForeground());
		      }
		  
		      return c;
		   }
		}
	
}


