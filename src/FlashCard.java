public class FlashCard {
	private String question;
	private String answer;
	
	public FlashCard(String question, String answer) {
		this.answer = answer;
		this.question = question;
	}
	
	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

}
