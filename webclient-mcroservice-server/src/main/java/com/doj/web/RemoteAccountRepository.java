package com.doj.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dinesh.Rajput
 *
 */
public class RemoteAccountRepository implements AccountRepository {
	
	public static final String HYPONATERMIA_SERVICE_URL = "http://HYPONATEMIA";
	public static final String ISOTONIC_SERVICE_URL = "http://ISOTONICITY";
	public static final String HYPOTONIC_SERVICE_URL = "http://HYPOTONICITY";
	public static final String HYPOVOLEMIA_SERVICE_URL = "http://HYPOVOLEMIA";
	public static final String URINESODUM_SERVICE_URL = "http://URINESODIUM";
	public static final String HYPERVOLEMIA_SERVICE_URL = "http://HYPERVOLEMIA";
	public static final String HYPERTONICITY_SERVICE_URL = "http://HYPERTONICITY";
	
	
	
	
	@Autowired
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	public RemoteAccountRepository(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	public List<Account> getAllAccounts() {
		Account[] accounts = restTemplate.getForObject(serviceUrl+"/accounts", Account[].class);
		return Arrays.asList(accounts);
	}

	public Account getAccount(String number) {
		return restTemplate.getForObject(serviceUrl + "/accounts/{id}",
				Account.class, number);
	}

	
	public String gethyponatemia(String serumSodium) {
		return restTemplate.getForObject(serviceUrl + "/hyponatemia/{serumSodium}",
				String.class, serumSodium);
	}

	@Override
	public String CheckHyponatremia(String serumSoduium, String serumOsmol, String hypovolemicSymptoms) {
		String hyponatermia = restTemplate.getForObject(HYPONATERMIA_SERVICE_URL+"/hyponatemia/{serumSodium}",String.class);
		if(hyponatermia.equalsIgnoreCase("hyponatemia")) {
			String hypertonicity = restTemplate.getForObject(HYPOTONIC_SERVICE_URL+"/hypotonicity/{serumOsmol}",String.class);
			if(hypertonicity.equalsIgnoreCase("hypotonic")) {
				String hypovolemia = restTemplate.getForObject(HYPOVOLEMIA_SERVICE_URL+"/hypovolemia/{hypovolemicSymptoms}",String.class);
					if(hypovolemia.equalsIgnoreCase("hypovolemia")) {
						String highUrineSodium = restTemplate.getForObject(URINESODUM_SERVICE_URL+"/UrineSodium/{serumSodium}",String.class);
							if(highUrineSodium.equalsIgnoreCase("highUrineSodium")) {								
								return "Diagnose3";
							}
							else {
								return "Diagnose4";
							}
					}
					else {
						String hypervolemia = restTemplate.getForObject(HYPERVOLEMIA_SERVICE_URL+"/Hypervolemia/{hypervolemicSymptoms}",String.class);
							if(hypervolemia.equalsIgnoreCase("hypervolemia")) {
								String highUrineSodium = restTemplate.getForObject(URINESODUM_SERVICE_URL+"/UrineSodium/{serumSodium}",String.class);
									if(highUrineSodium.equalsIgnoreCase("highUrineSodium")) {								
										return "Diagnose3";
									}
									else {
										return "Diagnose4";
									}
							} else {
								return "euvolemia";
								}
					}
				} else {
					String hypertonic= restTemplate.getForObject(HYPERTONICITY_SERVICE_URL+"/Hypertonicity/{serumOsmol}",String.class);
						if(hypertonic.equalsIgnoreCase("hypertonic")) {
							return "Diagnose 1";
							}
						else {
							return "Diagnose 2";
							}
					}
			} else {
				return null;
				}
			
		}
		
	
	
}


