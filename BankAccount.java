import java.util.Random;
import java.util.Scanner;


class BankAccount
{

	static Scanner input = new Scanner(System.in);		//For making it available to all methods.


	//Fields for bank account.
	private String accHolderName;				//Holds the Account holder name.
	private int accHolderPhoneNo;				//Holds Account holder's phone number.
	private String accHolderIFSCCode;			//Unique code for each account holder generated through random method.
	private double balanceAmount;				//stores the amount deposited by the holder.
	private double withdrawAmount;				//Amount to be withdrawn by the holder.
	private boolean accountExists;				//Sets to true only if account is successfully created.

	//Constants.
	static final double MINIMUM_BALANCE = 1000.0;		//Minimum balance to open as well as maintain the bank account.
	static final double DAILY_WITHDRAW_LIMIT = 5000.0;	//Maximum amount that can be withdrawn daily.
	
								//accordingly.
	//BankAccount constructor.
	BankAccount()
	{
		this.balanceAmount = 0.0;		//Intial
		this.withdrawAmount = 0.0;		//Condition.
		this.accountExists = false;
	}


		public String getName()
	{
		return accHolderName;
	}
	//End.


	//Creating new user account.
	public void createNewAccount()
	{
		getUserDetails();		//For taking inputs from user.
	}


	public void getUserDetails()		//Gets all the essential details of the user.
	{

		double tempDeposit;		//Temporary double, string 
		int tempPhoneNo;		//and integer variables for passing
		String tempName;		// them as arguments.

		tempName = readAccHolderName(); 		//Reads account user name and rerturn the same.
		setName(tempName);		//Reads account user name.

		tempPhoneNo = readAccHolderPhoneNo();		//Reads account user name and returns the same.
		setPhoneNo(tempPhoneNo);			//Sets account user phone number.

		tempDeposit = readInitialDepositAmount();	//For reading initial deposit done by user.
		if(tempDeposit<MINIMUM_BALANCE)		//Checks for minimum deposit done.
		{
			System.out.println("\n---------------Please try again and enter minimum deposit amount---------------\n");
			return;
		}

		setBalanceAmount(tempDeposit);	//If done, it sets the initial deposit amount.

		generateIFSCCode();		//For generating IFSC code.

		setAccountTruth(true);		//The account now exists.

		printSuccessMessage();		//Prints a message upon successful completion of creating the account.

	}
	//End.


	//Takes account holder name.
	public String readAccHolderName()
	{

		String tempName;		//Temporary string for holding name.
		System.out.print("\nEnter the name of the account holder : ");
		tempName = input.next();
		
		return tempName;

	}
	//End.


	//Takes account holder phone number,
	public int readAccHolderPhoneNo()
	{
	
		int tempPhoneNo;		//Temporary integer for holding phone number.
		System.out.print("\nEnter the phone number of the holder : ");
		tempPhoneNo = input.nextInt();
		
		return tempPhoneNo;

	}
	//End.


	//Deposit methods
	public void deposit()
	{
		double tempDeposit;
		tempDeposit = readDepositAmount();
		this.balanceAmount += tempDeposit;
		successfulTransactionMessage();
	}

	
	public double readInitialDepositAmount()	//For first time users.
	{

		System.out.print("\nMinimum deposit of Rs." + MINIMUM_BALANCE + " is mandatory");

		return readDepositAmount();

	}

	
	public double readDepositAmount()	//For Regular users.
	{

		double tempDeposit;

		System.out.print("\nEnter the deposit amount : ");
		tempDeposit = input.nextDouble();

		return tempDeposit;

	}
	//End.


	//Withdraw methods.
	public void withdraw()
	{
		double tempWithdraw;
		tempWithdraw = readWithdrawAmount();
		
		if(checkWithdrawalConditions(tempWithdraw)) //Executes withdrawal only if conditions are met.
		{
			this.executeWithdrawal(tempWithdraw);
			successfulTransactionMessage();
		}

		else		//Cancels transaction otherwise.
		{
			unSuccessfulTransactionMessage();
		}

	}


	public double readWithdrawAmount()		//Reads and returns withdraw requested by the user.
	{

		double readWithdraw;

		System.out.print("Enter the withdraw amount : ");
		readWithdraw = input.nextDouble();
		
		return readWithdraw;
	}


	public boolean checkWithdrawalConditions(double tempWithdraw)		//Checks for withdrawal conditions.
	{

		boolean withdrawalPossibility;

		if(tempWithdraw>MINIMUM_BALANCE)		//Minimum balance condtion.
		{
			System.out.println("Insufficient Balance!");
			withdrawalPossibility = false;
		}

		else if(tempWithdraw+this.withdrawAmount>DAILY_WITHDRAW_LIMIT || tempWithdraw>DAILY_WITHDRAW_LIMIT)	//Daily limit excceded condition.
		{
			System.out.println("Daliy Limit Exceeded.");
			withdrawalPossibility = false;
		}
		
		else			//Otherwise true.
		{
			withdrawalPossibility = true;
		}

		return withdrawalPossibility;

	}


	public void executeWithdrawal(double tempWithdraw)		//Executes withdrawal only if conditions are met.
	{

		this.balanceAmount -= tempWithdraw;
		this.withdrawAmount += tempWithdraw;
	
	}
	//End of withdraw methods.

	
	//IFSC code generator.
	public void generateIFSCCode()
	{
		Random ifsc = new Random();
		
		int intIFSC = ifsc.nextInt(100);
		
		String tempIFSC = new String("MYBANK" + Integer.toString(intIFSC)); //Generates a random ifsc code.
		setIFSCCode(tempIFSC);
	}
	//End.


	//Printing methods.	

	//Welcome message.
	public static void printWelcomeMessage()
	{
		System.out.println("\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-WELCOME TO MYBANK-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n\n");
	}

	//Successful acocunt creation message.
	public void printSuccessMessage()
	{
		System.out.println("\n------------------------------------------\n\tAccount created successfully!\n------------------------------------------");
	}

	//Successful transaction message.
	public void successfulTransactionMessage()
	{
		System.out.println("\n------------------------------------------\nTransaction successful!\n------------------------------------------");
	}

	//Unsuccessful transaction message.
	public void unSuccessfulTransactionMessage()
	{
		System.out.println("\n------------------------------------------\nTransaction unsuccessful!\n------------------------------------------");

	
	}
	
	//Displays balance.
	public void displayBalance()
	{
		System.out.println("Your balance is : " + getBalanceAmount());
	}

	//Display menu for user interface.
	public static void showMenu()
	{

		System.out.println("\n------------------------------------------");
		System.out.println("\t1. Create new account.");
		System.out.println("\t2. Display Balance amount.");
		System.out.println("\t3. Deposit money.");
		System.out.println("\t4. Withdraw money.");
		System.out.println("\t5. Exit.");
		System.out.println("------------------------------------------");
	}
	//End.


	//Getter methods.
	public int getPhoneNo()
	{
		return accHolderPhoneNo;
	}


	public double getBalanceAmount()
	{
		return balanceAmount;
	}


	public String getIFSCCode()
	{
		return accHolderIFSCCode;
	}


	public boolean getAccountTruth()
	{
		return accountExists;
	}
	//End.


	//Setter methods.
	public void setName(String tempName)
	{
		this.accHolderName = tempName;
	}


	public void setPhoneNo(int tempPhoneNo)
	{
		this.accHolderPhoneNo = tempPhoneNo;
	}


	public void setBalanceAmount(double tempBalance)
	{
		this.balanceAmount = tempBalance;
	}

	
	public void setIFSCCode(String tempIFSC)
	{
		this.accHolderName = tempIFSC;
	}


	public void setAccountTruth(boolean truthValue)
	{
		this.accountExists = truthValue;
	}
	//End.

}//End of class.


class BankAccountMainClass
{
	static Scanner input = new Scanner(System.in);


	//main method.
	public static void main(String[] args)
	{

		BankAccount userAccount = new BankAccount();
		
		int choice, userCounter = 0;
		boolean accountCreated =  false;

		userAccount.showMenu();

		while(true)
		{
			System.out.println("\n\n------------------------------------------");	
			System.out.print("\tEnter your choice  ---->  ");
			choice = input.nextInt();

			switch(choice)
			{
				case 1:
					userAccount = new BankAccount();
					userAccount.createNewAccount();
					break;
		
				case 2:
					if(userAccount.getAccountTruth()==true)
						userAccount.displayBalance();
					else
						System.out.println("Account does not exist");
					break;

				case 3:
					if(userAccount.getAccountTruth()==true)
						userAccount.deposit();
					else
						System.out.println("Account does not exist");
					break;

				case 4:
					if(userAccount.getAccountTruth()==true)
						userAccount.withdraw();
					else
						System.out.println("Account does not exist");
					break;

				case 5:
					System.out.println("Thank You for using our services");
					System.exit(0);
			}
		}
	}
}
