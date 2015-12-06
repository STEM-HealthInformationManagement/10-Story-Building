package org.njcuacm.tenstory;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class About extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        setTitle("About Ten Story Building");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView) findViewById(R.id.aboutTextView);
        textView.setText("Ten-Story Building is a handheld app designed to teach one of the more difficult and important aspects of English for non-native speakers—the phrasal verb (Liao & Fukuya,, 2004; Lui, 2011).  The target audience is an intermediate or advanced ESL speakers the are competent in expressive and receptive vocabulary but would need assistance on the syntax and idiomatic expressions of the phrasal verbs.\n" +
                "Though there is debate on a standard definition of the phrasal verb, a working definition is a verb + prepositional phrase whose combination is relatively arbitrary (e.g. succeed at, based on) (Nassaj  & Tian, 2012).  Phrase verbs also include the verb ‘to be’ with the addition of adjective + prepositional  phrase (e.g. similar to)  and adverb + prepositional phrase (e.g. friendly with) with relatively arbitrary combinations. \n" +
                "\n" +
                "The combinations are considered relatively arbitrary because sometimes a figurative or literal connection can be deduced (“fill up” “hand out”, but often the combination of the words are (e.g. “depend on” instead of “depend in”, “look up” instead of “look for”, and “ashamed of” “ashamed in”). We used a set of 63 Phrasal Verbs.  This set consisted of the common phrasal verbs (Lui, 2011) and augmented by an expert selection of difficult but important  phrases for non-native speakers. \n" +
                "\n" +
                "The app is a game-like teaching environment in which a player follows stories through the social media posts, text messages, and emails of different people. Each exchange is a unique, high interest story, aimed to engage college-age students.  Authenticity is a key feature, but the stories follow the conventions of Standard English.  \n" +
                "\n" +
                "To move forward in the story, a user must correctly select a phrasal verb in context.  One important feature is that each phrasal verb first appears in context before a player is asked to use it in context, so a user can remember the use or scroll back to reread it –the stories try to ‘teach’ the phrase before a player is assessed on it.\n" +
                " There is no profanity, but the stories do contain adult situations (drug use, marital infidelity, sexual harassment), addressed in respectful and responsible ways.  We would recommend this app for users 18 and older.  We wanted an app that would specifically address the interests of college age adults, yet without offending sensibilities or cultures.   \n" +
                "\n" +
                "The phrasal verb lends itself to these authentic and emotionally charged stories.  It is a syntactical unit, a piece of language, that conveys a united and unique meaning.  Authentic conversations about important events and feelings seemed to be the most important place for a phrasal verb. \n" +
                "Enjoy.\n" +
                "\n" +
                "\n" +
                "Works Cited\n" +
                "\n" +
                "Lui, D. (2011).  The most frequently used English Phrasal verbs in American and British English: A multicorpus examination.  TESOL Quarterly, Vol. 45 (4)\n" +
                "Hayati A, Jalilifar A, & Mashhadi, A. (2013). Using short message service (SMS) to teach English idioms to EFL students.   British Journal of Educational Technology, 44 (1).\n" +
                "Kim, H & Kwon, Y. (2012). Exploring smartphone applications for effective mobile-assisted language learning. Multimedia-Assisted Language Learning, 15(1), 31-57.\n" +
                "Liao, Y & Fukuya, Y. (2004) Avoidance of Phrasal Verbs: The case of Chinese learners of English.  Language Learning 54 (2). pp193–226\n" +
                "Nassaji, H. & Tian, J. (2012) Collaborative and individual output tasks and their effects on learning English phrasal verbs.  The Modern Language Journal, 96 (3)\n" +
                "White, B. (2012). A conceptual approach to the instruction of phrasal verbs. The Modern Language Journal, 96(3).\n");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //The onBackPressed() method will go to the previous activity...ONLY if it was not FINISHED.
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
