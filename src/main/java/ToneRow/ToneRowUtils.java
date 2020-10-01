package ToneRow;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 3/17/16.
 */
class ToneRowUtils {
    private final static String[] NOTE_HOLDER = {"NoteOne", "NoteTwo", "NoteThree", "NoteFour", "NoteFive", "NoteSix",
        "NoteSeven", "NoteEight", "NoteNine", "NoteTen", "NoteEleven", "NoteTwelve"};
    private static String[] noteSlotArray = new String[12];
    private static String[][] matrix = new String[12][12];
    private static int[] rowNums = new int[12];

    /**
     * @param noteSlotArray The noteSlotArray string array
     *                      This array holds the string values of the notes the user spoke
     * @return returns the noteValues array
     * Returns the array that holds the numeric note values
     */
    private static int[] getNoteNums(String[] noteSlotArray) {

        int[] noteValues = new int[noteSlotArray.length];
        for (int i = 0; i < noteSlotArray.length; i++) {
            // Code for calculating prime form

            Map<String, Integer> dictionary = new HashMap<>();
            dictionary.put("C", 0);
            dictionary.put("C.", 0);
            dictionary.put("C. SHARP", 1);
            dictionary.put("C SHARP", 1);
            dictionary.put("C#", 1);
            dictionary.put("DB", 1);
            dictionary.put("D. FLAT", 1);
            dictionary.put("D FLAT", 1);
            dictionary.put("D", 2);
            dictionary.put("D.", 2);
            dictionary.put("D#", 3);
            dictionary.put("D. SHARP", 3);
            dictionary.put("D SHARP", 3);
            dictionary.put("Eb", 3);
            dictionary.put("E. FLAT", 3);
            dictionary.put("E FLAT", 3);
            dictionary.put("E", 4);
            dictionary.put("E.", 4);
            dictionary.put("F", 5);
            dictionary.put("F.", 5);
            dictionary.put("F#", 6);
            dictionary.put("F. SHARP", 6);
            dictionary.put("F SHARP", 6);
            dictionary.put("Gb", 6);
            dictionary.put("G. FLAT", 6);
            dictionary.put("G", 7);
            dictionary.put("G.", 7);
            dictionary.put("G#", 8);
            dictionary.put("G. SHARP", 8);
            dictionary.put("G SHARP", 8);
            dictionary.put("Ab", 8);
            dictionary.put("A. FLAT", 8);
            dictionary.put("A FLAT", 8);
            dictionary.put("A", 9);
            dictionary.put("A.", 9);
            dictionary.put("A#", 10);
            dictionary.put("A. SHARP", 10);
            dictionary.put("A SHARP", 10);
            dictionary.put("BB", 10);
            dictionary.put("B. FLAT", 10);
            dictionary.put("B FLAT", 10);
            dictionary.put("B", 11);
            dictionary.put("B.", 11);

            noteValues[i] = dictionary.get(noteSlotArray[i].toUpperCase());
        }
        return noteValues;
    }

    /**
     * @param intent The intent that is being passed in
     *               The intent that is being passed into the method that has the slots
     * @return Returns the speech response of the PrimeForm
     */
    static SpeechletResponse calculateMatrix(final Intent intent) {

        String speechText = "The prime form is, ";
        String repromptText = "";

        Map<String, Slot> slots = intent.getSlots();
        for (int i = 0; i < noteSlotArray.length; i++) {
            // Get the note slot from the list of slots.
            Slot noteNameSlot = slots.get(NOTE_HOLDER[i]);
            noteSlotArray[i] = noteNameSlot.getValue();
        }

        rowNums = MatrixCalculator.getRowNums(noteSlotArray);
        matrix = MatrixCalculator.getMatrix(rowNums);

//        int[] noteValues = getNoteNums(noteSlotArray);
//        PitchSet set = new PitchSet(noteValues);
//        PitchSet answerSet = set.getPrimeForm();
//        speechText += answerSet.toString();

        return getSpeechletResponse(speechText, repromptText, true);
    }

    /**
     * Creates and returns a {@code SpeechletResponse} with a welcome message.
     *
     * @return SpeechletResponse spoken and visual welcome message
     */
    static SpeechletResponse getWelcomeResponse() {
        // Create the welcome message.
        String speechText =
                "Welcome to the Music Theory helper! Ask what the prime or normal form is of a set by saying," +
                        " what is the prime, or normal, form of A, C, and C sharp. " + "Be sure to leave a little bit of" +
                        " space between each letter.";
        String repromptText =
                "Ask me what the prime form is of A, C, and C#";

        return getSpeechletResponse(speechText, repromptText, true);
    }

    /**
     * @return Returns speech response
     * Returns the thank you message
     */
    static SpeechletResponse getEndResponse() {
        // Create the end message.
        String speechText =
                "Thanks for using the music theory helper! Goodbye!";
        String repromptText =
                "Thanks for using the music theory helper! Goodbye!";

        return getSpeechletResponse(speechText, repromptText, true);

    }

    /**
     * Returns a Speechlet response for a speech and reprompt text.
     */
    static SpeechletResponse getSpeechletResponse(String speechText, String repromptText,
                                                  boolean isAskResponse) {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Session");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        if (isAskResponse) {
            // Create reprompt
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText(repromptText);
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);

            return SpeechletResponse.newAskResponse(speech, reprompt, card);

        } else {
            return SpeechletResponse.newTellResponse(speech, card);
        }
    }
}