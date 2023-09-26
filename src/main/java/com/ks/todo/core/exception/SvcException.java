package com.ks.todo.core.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Customized Runtime Exception
 * 
 * @author Ken Shen
 * @version 1.0
 */
public class SvcException extends RuntimeException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8510233691806992017L;

	protected String msg;

	protected String exId;

	protected Object[] params;

	protected List<SvcException> nstExs;

	protected Throwable wrpEx;

	/**
	 * Initialize SvcException
	 */
	public SvcException() {
		super();
		gnrExUid();
	}

	/**
	 * Initialize SvcException
	 * 
	 * @param msg
	 */
	public SvcException(String msg) {
		super(msg);
		this.msg = msg;
		gnrExUid();
	}

	/**
	 * Initialize SvcException
	 * 
	 * @param msg
	 * @param params
	 */
	public SvcException(String msg, Object[] params) {
		super(msg);
		this.params = params;
		gnrExUid();
	}

	/**
	 * Initialize SvcException
	 * 
	 * @param ex
	 */
	public SvcException(Throwable ex) {
		super(ex);
		this.wrpEx = ex;
		gnrExUid();
	}

	/**
	 * Initialize SvcException
	 * 
	 * @param msg
	 * @param ex
	 */
	public SvcException(String msg, Throwable ex) {
		this(msg);
		this.wrpEx = ex;
		gnrExUid();
	}

	/**
	 * Initialize SvcException
	 * 
	 * @param msg
	 * @param params
	 * @param ex
	 */
	public SvcException(String msg, Object[] params, Throwable ex) {
		this(msg, params);
		this.params = params;
		this.wrpEx = ex;
		gnrExUid();
	}

	/**
	 * Add Nested Exception
	 * 
	 * @param svcEx
	 */
	public void addNstEx(SvcException svcEx) {
		if (null == nstExs) {
			nstExs = new ArrayList<>();
		}
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the exId
	 */
	public String getExId() {
		return exId;
	}

	/**
	 * @param exId the exId to set
	 */
	public void setExId(String exId) {
		this.exId = exId;
	}

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}

	/**
	 * @return the nstExs
	 */
	public List<SvcException> getNstExs() {
		return nstExs;
	}

	/**
	 * @param nstExs the nstExs to set
	 */
	public void setNstExs(List<SvcException> nstExs) {
		this.nstExs = nstExs;
	}

	/**
	 * @return the wrpEx
	 */
	public Throwable getWrpEx() {
		return wrpEx;
	}

	/**
	 * @param wrpEx the wrpEx to set
	 */
	public void setWrpEx(Throwable wrpEx) {
		this.wrpEx = wrpEx;
	}

	/**
	 * @see java.lang.Throwable#getCause()
	 */
	@Override
	public synchronized Throwable getCause() {
		return this.wrpEx;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder exBuilder = new StringBuilder();

		exBuilder.append("SvcException { ").append("Message=").append(msg).append(" Exception ID=").append(exId)
				.append(" Parameters=").append(Arrays.toString(params)).append(" Nested Exceptions=").append(nstExs)
				.append(" Wrapped Exception=").append(wrpEx).append(" }");

		return exBuilder.toString();
	}
	
	/**
	 * Generate Exception ID
	 */
	private void gnrExUid() {
		UUID uid = UUID.randomUUID();
		
		int hashUid = uid.toString().hashCode();
		this.exId = String.valueOf(hashUid).replace("-", "");
	}
}
