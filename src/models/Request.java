package models;
import models.enums.RequestStatus;


public class Request
{
	private String requestId;
	private String deviceId;
	private String requesterId;
	private RequestStatus status;

	Request(String requestId, String deviceId, String requesterId, RequestStatus status)
	{
		this.requestId = requestId;
		this.deviceId = deviceId;
		this.requesterId = requesterId;
		this.status = status;
	}
	
	public String getRequestID()
	{
		return this.requestId;
	}
	public String getDeviceID()
	{
		return this.deviceId;
	}
	public String getrequesterId()
	{
		return this.requesterId;
	}
	public RequestStatus getStatus()
	{
		return this.status;
	}

	public void setRequestID(String requestId)
	{
		this.requestId = requestId;
	}
	public void setDeviceID(String deviceId)
	{
		this.deviceId = deviceId;
	}
	public void setRequesterID(String requesterId)
	{
		this.requesterId = requesterId;
	}
	public void setStatus(RequestStatus status)
	{
		this.status = status;
	}

	public void requestDetails()
	{
		System.out.print("Request Id : "+requestId+
				"\nDevice Id : "+deviceId+
				"\nRequester Id : "+requesterId+
				"\nStatus : "+status);
	}

}
