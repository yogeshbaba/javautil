package util.file;

public class FileResponse extends Response {
	
	String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public static FileResponse newInstance(boolean isSuccess, Exception e, String content) {
		FileResponse response = new FileResponse();
		response.isSuccess = isSuccess;
		response.e = e;
		response.content = content;
		return response;
	}

}
