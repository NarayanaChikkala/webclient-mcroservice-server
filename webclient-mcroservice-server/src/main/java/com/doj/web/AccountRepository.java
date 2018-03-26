/**
 * 
 */
package com.doj.web;

import java.util.List;

/**
 * @author Dinesh.Rajput
 *
 */
public interface AccountRepository {
	
	List<Account> getAllAccounts();
	
	Account getAccount(String number);

	String gethyponatemia(String serumSodium);
	
	String CheckHyponatremia(String serumSoduium,String serumOsmol,String hypervolemicSystems);
}
