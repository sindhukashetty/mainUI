package com.cg.ics.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.ics.exceptions.ICSException;
import com.cg.ics.model.Claim;
import com.cg.ics.model.Policy;
import com.cg.ics.service.ICSService;
import com.cg.ics.service.ICSServiceImpl;

public class MainUI {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Policy policy1 = new Policy();

		System.out.println("Enter the username to login");
		String userName = scanner.nextLine();
		System.out.println("Enter the password to login");
		String password = scanner.nextLine();

		ICSService service = new ICSServiceImpl();
		int choice = 0;
		try {
			boolean loginFlag = service.userLogin(userName, password);
			// System.out.println(loginFlag);

			if (loginFlag) {

				List<Policy> list = new ArrayList<Policy>();

				list = service.getPolicyList();

				if (list.size() > 0) {
					System.out.println(" Policy Number " + " Policy Premium "
							+ " Bussiness Name ");
					for (Policy policy : list) {
						System.out.println(policy.getPolicyNumber() + "   "
								+ policy.getPolicyPremium() + "   "
								+ policy.getBusinessSegement().getBusSegName());
					}
				} else {
					System.out.println("no data present");
				}

				System.out.println("1.Create Claim");
				System.out.println("2.View Status Of Claim");

				System.out.println("Enter choice");
				choice = scanner.nextInt();
				switch (choice) {
				case 1:

					scanner.nextLine();
					System.out.println("Claim Reason");
					String claimReason = scanner.nextLine();
					System.out.println("Accident Location");
					String accidentLocationStreet = scanner.nextLine();
					System.out.println("Accident City");
					String accidentCity = scanner.nextLine();
					System.out.println("Accident State");
					String accidentState = scanner.nextLine();
					System.out.println("Accident Zip");
					Long accidentZip = scanner.nextLong();
					System.out.println("Enter the required Policy Number");
					Long policyNumber = scanner.nextLong();
					System.out.println("Claim Type");
					System.out.println("1.Earthquake");
					System.out.println("2.Collision");
					System.out.println("3.Flood");
					System.out.println("4.Hurricane");

					System.out.println("Enter your claim type");

					Claim claim = new Claim();

					int claimType = scanner.nextInt();
					switch (claimType) {
					case 1:
						claim.setClaimType("Earthquake");

						break;
					case 2:
						claim.setClaimType("Collision");

						break;
					case 3:
						claim.setClaimType("Flood");

						break;
					case 4:
						claim.setClaimType("Hurricane");

						break;

					default:
						System.out.println("Enter the valid claimtype");
						break;
					}
					claim.setClaimReason(claimReason);
					claim.setAccidentLocationStreet(accidentLocationStreet);
					claim.setAccidentCity(accidentCity);
					claim.setAccidentState(accidentState);
					claim.setAccidentZip(accidentZip);
					policy1.setPolicyNumber(policyNumber);

					service.claimCreation(claim, policy1);
					System.out.println("Claim created");

					break;
				case 2:
					
					List<Claim> claimList=new ArrayList<Claim>();
					claimList=service.viewClaim();

					break;

				default:
					System.out.println("Enter the correct choice");
					System.err.println("Please enter the valid digits");

					break;
				}
			}
		} catch (ICSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
