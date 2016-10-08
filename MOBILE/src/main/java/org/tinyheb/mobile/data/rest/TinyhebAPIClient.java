/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinyheb.mobile.data.rest;

/**
 *
 * @author haubi
 */
public class TinyhebAPIClient implements TinyhebAPICallback{


	public interface Listener {
		public void onAPIServerFound();
		public void onAPIServerMissing();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}
	
	private Listener listener;
	private final TinyhebAPICollaborator apiCollaborator;
    private int resultCode;
 
	public TinyhebAPIClient(TinyhebAPICollaborator collaborator) {
		this.apiCollaborator = collaborator;
	}
	
	public TinyhebAPIClient() {
		this.apiCollaborator = new TinyhebAPICollaborator();
	}
 
	public void checkURL() {
		apiCollaborator.checkIfURLReachable(this);
	}
 
	public boolean isAvailable() {
		return this.resultCode == 200;
	}
    
    @Override
    public void onResponse(int responseCode) {
        this.resultCode = responseCode;
        if (isAvailable())
        	listener.onAPIServerFound();
        else
        	listener.onAPIServerMissing();
    }

}
