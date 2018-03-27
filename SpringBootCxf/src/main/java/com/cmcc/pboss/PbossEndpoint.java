package com.cmcc.pboss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cmcc.services.pboss.pbossservice.InterBossRequest;
import com.cmcc.services.pboss.pbossservice.InterBossResponse;
import com.cmcc.services.pboss.pbossservice.PbossManager;
import com.symbio.services.mock.allnetservice.AllnetServicePort;
import com.symbio.services.mock.allnetservice.PersonInfo;
import com.symbio.services.mock.allnetservice.PersonOrderInfoRequest;
import com.symbio.services.mock.allnetservice.PersonOrderInfoResponse;
import com.symbio.services.mock.allnetservice.ProdInfo;
import com.symbio.services.mock.hlrservice.ECMemberOrderInfoRequest;
import com.symbio.services.mock.hlrservice.ECMemberOrderInfoResponse;
import com.symbio.services.mock.hlrservice.HlrServicePort;


public class PbossEndpoint  implements PbossManager{

	
	private static Logger LOGGER = LoggerFactory.getLogger(PbossEndpoint.class);

	@Autowired
	private AllnetServicePort allnetService;
	
	@Autowired
	private HlrServicePort hlrService;
	
	
	@Override
	public InterBossResponse interBossCall(InterBossRequest interBossRequest) {
		PersonOrderInfoRequest personOrderInfoRequest = new PersonOrderInfoRequest();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setApplyChannel("01");
		personInfo.setOprCode("01");
		personInfo.setOprSeq("2001BIP2B96420160317121235010004");
		personInfo.setOprTime("20171010122630");
		personOrderInfoRequest.setPersonInfo(personInfo);
		
		ProdInfo prodInfo = new ProdInfo();
		personOrderInfoRequest.setProdInfo(prodInfo);
		prodInfo.setProdId("P10000000001001");
		prodInfo.setProdInstExpTime("20551010122630");
		prodInfo.setSubsId("S10000010001");
		PersonOrderInfoResponse personOrderInfoResponse=allnetService.syncUserInfo(personOrderInfoRequest);
		
		InterBossResponse interBossResponse = new InterBossResponse();
		interBossResponse.setOprSeq("2001BIP2B96420160317121235010004");
		interBossResponse.setRspCode("11001");
		interBossResponse.setRspDesc("SUCCESS0001: PersonOrderInfo is synced");
		if(!personOrderInfoResponse.getRspCode().equalsIgnoreCase("11001")){
			interBossResponse.setOprSeq("2001BIP2B96420160317121235010004");
			interBossResponse.setRspCode("21001");
			interBossResponse.setRspDesc("FAIL:  UserInfo is not synced in ALLNET system. ");
			return interBossResponse;
		}
		
		
		ECMemberOrderInfoRequest ecMemberOrderInfoRequest = new ECMemberOrderInfoRequest();
		ECMemberOrderInfoResponse  ecMemberOrderInfoResponse= hlrService.syncCont(ecMemberOrderInfoRequest);
		if(!ecMemberOrderInfoResponse.getRspCode().equalsIgnoreCase("11001")){
			interBossResponse.setOprSeq("2001BIP2B96420160317121235010004");
			interBossResponse.setRspCode("21001");
			interBossResponse.setRspDesc("FAIL:  UserInfo is not synced in HLR system. ");
		}
		
		
		return interBossResponse;
	}
    
   

    
   
	
}

