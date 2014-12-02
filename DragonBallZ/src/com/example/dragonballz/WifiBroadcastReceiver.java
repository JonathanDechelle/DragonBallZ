package com.example.dragonballz;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.widget.Toast;

public class WifiBroadcastReceiver	extends BroadcastReceiver {

	    private WifiP2pManager mManager;
	    private Channel mChannel;
	    //private MainActivity mActivity;
	    private List<WifiP2pDevice> Peers = new ArrayList<WifiP2pDevice>();
	    private PeerListListener PeerListListener;
	    private Context context;
	   
	 
	    public WifiBroadcastReceiver (WifiP2pManager manager, Channel channel, MainActivity activity) 
	    {
	        super();
	        this.mManager = manager;
	        this.mChannel = channel;
	        context = activity.getApplicationContext();
	        //  this.mActivity = activity;   
	        
	        PeerListListener = new PeerListListener() 
	        {
				
				@Override
				public void onPeersAvailable(WifiP2pDeviceList peersList) 
				{
					Peers.clear();
					Peers.addAll(peersList.getDeviceList());
					
					if(Peers.size() == 0)
					{
						System.out.println("No Device found");
						System.out.println("-------------");
						Toast.makeText(context, "Aucun Appareil trouvés", Toast.LENGTH_SHORT).show();
						return;
					}
					else
					{
						System.out.println("Device found");
						connect();
					}
				}
			};
	    }
	    
	    @Override
	    public void onReceive(Context context, Intent intent) 
	    {
	    	String action = intent.getAction();
	    	
		    if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) 
	        {
	            // Check to see if Wi-Fi is enabled and notify appropriate activity
	        	int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
	           
	        	if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) 
	        	{
	        		System.out.println("-------------");
	        		System.out.println("Wifi Activate");
	        	
	             mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() 
	             {
	            	 
						@Override
						public void onSuccess() 
						{
							System.out.println("search succeeded");
							mManager.requestPeers(mChannel, PeerListListener);
						}
						
						@Override
						public void onFailure(int reason) 
						{
							System.out.println("search failed");
						}
					});
	        	} 
	        	else 
	        	{
	        		 Toast.makeText(context, "Wifi Non Activé", Toast.LENGTH_SHORT).show();
	            }
	        } 
	        
	        else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) 
	        {
	            if (mManager != null) mManager.requestPeers(mChannel, PeerListListener);
	        } 
	        
	        else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) 
	        {
	            // Respond to new connection or disconnections
	        }
	        
	        else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) 
	        {
	            // Respond to this device's wifi state changing
	        }
	        
		  }
	   
	    public void connect()
	    {
	    	//Pick the first device found on the network
	    	final WifiP2pDevice device = Peers.get(0);
	    	
	    	WifiP2pConfig config = new WifiP2pConfig();
	    	config.deviceAddress = device.deviceAddress;
	    	config.wps.setup = WpsInfo.PBC;
	    	
	    	mManager.connect(mChannel, config, new ActionListener() 
	    	{
				
				@Override
				public void onSuccess() 
				{
					Toast.makeText(context, "It's work for " + device.deviceName, Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onFailure(int reason)
				{
					switch(reason)
					{
					case 0: Toast.makeText(context, "internal error for " + device.deviceName, Toast.LENGTH_SHORT).show();
					System.out.println("internal error for " + device.deviceName);
					break;
					
					case 1: Toast.makeText(context, "p2p is unsupported for " + device.deviceName, Toast.LENGTH_SHORT).show();
					System.out.println("p2p is unsupported for " + device.deviceName);
					break;
					
					case 2: Toast.makeText(context,  "the framework is busy for " + device.deviceName, Toast.LENGTH_SHORT).show();
					System.out.println("the framework is busy for " + device.deviceName);
					break;
					}
					
					Toast.makeText(context, "Connect failed. Retry.",
	                        Toast.LENGTH_SHORT).show();
					System.out.println("Connect failed. Retry.");
					System.out.println("-------------");
				}
			});
	    	
	    }
	    
}
