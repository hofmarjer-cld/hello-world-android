package com.example.helloworld;

/*
    MainActivity.java - The Heart of Your Android App
    =================================================
    
    🎯 WHAT IS THIS FILE?
    This is your app's main Activity - the first screen users see when they open your app.
    Think of it like the "main()" function in other programming languages, but for Android.
    
    📚 KEY ANDROID CONCEPTS YOU'LL LEARN:
    1. Activity Lifecycle - How Android manages your app's life and death
    2. User Interface - How to show things on screen
    3. Event Handling - How to respond to user interactions
    4. Logging - How to debug your app professionally
    5. Resource Management - How Android handles different screen sizes/languages
    
    🧠 ACTIVITY LIFECYCLE EXPLAINED:
    Android manages memory aggressively. Your app can be:
    - Created: User opens app
    - Started: App becomes visible  
    - Resumed: User can interact with app
    - Paused: Another app comes to foreground (phone call, notification)
    - Stopped: App is no longer visible
    - Destroyed: Android kills app to free memory
    
    📱 WHY IS LIFECYCLE IMPORTANT?
    - Save user's work before app gets killed
    - Resume exactly where user left off
    - Release resources when not needed
    - Handle interruptions gracefully (phone calls, etc.)
*/

// 📦 IMPORTS - The Tools We Need
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
    🔍 IMPORT EXPLANATION:
    - Activity: Base class for all screens in Android
    - Bundle: Container for data passed between Activities
    - Log: Professional debugging tool (like console.log in JavaScript)
    - View: Base class for all UI elements
    - Button: Clickable button widget
    - TextView: Widget that displays text
    - Toast: Small popup message to user
*/

/**
 * 🏠 MainActivity - Your App's Home Screen
 * 
 * 📚 EXTENDS Activity EXPLANATION:
 * By extending Activity, our class inherits all the Android system integration:
 * - Automatic lifecycle method calls
 * - UI rendering capabilities  
 * - System event handling
 * - Memory management
 * 
 * 🎯 THINK OF IT LIKE:
 * Activity is like a "smart frame" that Android provides.
 * You just need to tell it what to display and how to behave.
 */
public class MainActivity extends Activity {
    
    /*
        🏷️ LOG TAG - Your App's Debug Signature
        =======================================
        
        🎯 PURPOSE: Identifies your app's log messages among thousands of system logs
        📱 USAGE: Log.d(TAG, "Your message here")
        🔍 FILTERING: In logcat, filter by "HelloWorldApp" to see only your messages
        
        💡 PROFESSIONAL TIP:
        Always use a consistent TAG across your app for easy debugging.
        Many developers use the class name: private static final String TAG = "MainActivity";
    */
    private static final String TAG = "HelloWorldApp";
    
    /*
        🧩 UI COMPONENT REFERENCES
        ==========================
        
        🎯 WHY PRIVATE FIELDS?
        We store references to UI elements as private fields so we can:
        - Access them from any method in this Activity
        - Modify their properties (text, color, visibility)
        - Set up event listeners (click handlers)
        
        📱 THESE WILL CONNECT TO:
        - welcomeText: TextView showing "Hello World!" message
        - clickMeButton: Button that users can tap
        - clickCountText: TextView showing how many times button was clicked
    */
    private TextView welcomeText;
    private Button clickMeButton;
    private TextView clickCountText;
    
    /*
        📊 APP STATE VARIABLES
        =====================
        
        🎯 PURPOSE: Track what's happening in your app
        📱 EXAMPLES:
        - clickCount: How many times user clicked the button
        - userPoints: Points in a game
        - isLoggedIn: Whether user is authenticated
        - currentLevel: Level in a game
        
        💾 LIFECYCLE CONSIDERATION:
        These variables get reset when Android destroys/recreates your Activity.
        For persistent data, you'd use SharedPreferences, databases, or files.
    */
    private int clickCount = 0;
    
    /*
        🏗️ onCreate() - THE BIRTH OF YOUR ACTIVITY
        ==========================================
        
        📚 WHAT IS onCreate()?
        This method is called when Android creates your Activity for the first time.
        It's like a constructor, but specifically for Android components.
        
        🎯 WHAT HAPPENS IN onCreate():
        1. Android creates Activity object in memory
        2. Calls onCreate() to let you initialize your app
        3. You set up UI, variables, event listeners, etc.
        4. After onCreate() completes, Activity becomes visible
        
        📱 REAL-WORLD ANALOGY:
        onCreate() is like setting up a new office:
        - Arrange furniture (set up UI layout)
        - Connect phone and internet (set up event listeners)
        - Put nameplate on door (set title, icon)
        - Organize desk supplies (initialize variables)
        
        🔄 LIFECYCLE TIMING:
        onCreate() → onStart() → onResume() → User can interact with app
        
        ⚠️ IMPORTANT RULES:
        1. Always call super.onCreate(savedInstanceState) first
        2. Always call setContentView() to show your UI
        3. Initialize variables and UI components here
        4. Don't do heavy work here - it blocks the UI thread
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 🚨 CRITICAL: Always call parent's onCreate first
        // This lets Android do its system-level initialization
        super.onCreate(savedInstanceState);
        
        // 📱 PROFESSIONAL LOGGING: Track when Activity is created
        Log.d(TAG, "🏗️ onCreate() called - Activity is being created!");
        Log.d(TAG, "📊 Initial click count: " + clickCount);
        
        /*
            🎨 CREATE UI PROGRAMMATICALLY
            =============================
            
            Since we want to maintain GitHub Actions compatibility,
            we'll create the UI in code instead of using XML layouts.
            This eliminates resource file dependencies.
        */
        
        // Create main welcome text
        welcomeText = new TextView(this);
        welcomeText.setText("🌟 Welcome to Your First Android App! 🌟\nBuilt with GitHub Actions CI/CD");
        welcomeText.setTextSize(20);
        welcomeText.setPadding(50, 50, 50, 30);
        welcomeText.setGravity(android.view.Gravity.CENTER);
        
        // Create interactive button
        clickMeButton = new Button(this);
        clickMeButton.setText("Click Me!");
        clickMeButton.setTextSize(18);
        clickMeButton.setPadding(40, 20, 40, 20);
        
        // Create click counter display
        clickCountText = new TextView(this);
        clickCountText.setTextSize(16);
        clickCountText.setPadding(50, 20, 50, 50);
        clickCountText.setGravity(android.view.Gravity.CENTER);
        
        // Set initial click count display
        updateClickCountDisplay();
        
        // Create a vertical layout programmatically
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setGravity(android.view.Gravity.CENTER);
        layout.setPadding(30, 30, 30, 30);
        
        // Add views to layout
        layout.addView(welcomeText);
        layout.addView(clickMeButton);
        layout.addView(clickCountText);
        
        // Set the layout as our content view
        setContentView(layout);
        
        Log.d(TAG, "🎨 UI created programmatically");
        Log.d(TAG, "🔗 UI components initialized");
        
        /*
            👆 EVENT HANDLING - RESPONDING TO USER INTERACTIONS
            ==================================================
            
            🎯 WHAT IS setOnClickListener()?
            This method tells Android: "When user taps this button, run this code"
            
            📱 EVENT-DRIVEN PROGRAMMING CONCEPT:
            Your app doesn't run continuously. Instead, it sleeps until something happens:
            - User clicks button → onClick() runs
            - Phone rotates → onConfigurationChanged() runs  
            - App loses focus → onPause() runs
            - User types text → onTextChanged() runs
            
            🧠 ANONYMOUS INNER CLASS EXPLAINED:
            new View.OnClickListener() { ... } creates a small object whose only job
            is to handle button clicks. It's like hiring a specialist just for this task.
            
            💡 MODERN ALTERNATIVE - Lambda Expressions:
            Modern Java allows: clickMeButton.setOnClickListener(v -> handleButtonClick());
            But explicit OnClickListener is clearer for learning.
        */
        clickMeButton.setOnClickListener(new View.OnClickListener() {
            /*
                🎯 onClick() - BUTTON CLICK HANDLER
                ==================================
                
                This method runs every time user taps the button.
                The 'v' parameter is the View (button) that was clicked.
            */
            @Override
            public void onClick(View v) {
                // 📊 Track button clicks for educational purposes
                Log.d(TAG, "🖱️ Button clicked! Processing click #" + (clickCount + 1));
                
                // Call our custom method to handle the click logic
                handleButtonClick();
            }
        });
        
        Log.d(TAG, "👆 Button click listener set up successfully");
        Log.d(TAG, "✅ onCreate() completed - Activity ready for user interaction!");
    }
    
    /*
        🎯 CUSTOM METHOD - HANDLE BUTTON CLICK LOGIC
        ============================================
        
        🧠 WHY SEPARATE METHOD?
        Instead of putting all logic in onClick(), we create a separate method:
        - Easier to read and understand
        - Can be called from other places if needed
        - Easier to test
        - Follows "Single Responsibility Principle"
        
        📱 WHAT THIS METHOD DOES:
        1. Increment click counter
        2. Update UI to show new count
        3. Show encouraging message to user
        4. Log the event for debugging
    */
    private void handleButtonClick() {
        // 📈 Increment the click counter
        clickCount++;
        
        Log.d(TAG, "📊 Click count updated to: " + clickCount);
        
        // 🔄 Update the UI to show new click count
        updateClickCountDisplay();
        
        // 🎉 Show encouraging toast message to user
        showEncouragingMessage();
        
        // 🏆 Special celebration for milestone clicks
        if (clickCount == 10) {
            Log.d(TAG, "🏆 Milestone reached: 10 clicks!");
            Toast.makeText(this, "🏆 Wow! 10 clicks! You're getting the hang of this!", Toast.LENGTH_LONG).show();
        } else if (clickCount == 25) {
            Log.d(TAG, "🏆 Milestone reached: 25 clicks!");
            Toast.makeText(this, "🚀 25 clicks! You're an Android pro!", Toast.LENGTH_LONG).show();
        }
    }
    
    /*
        🔄 UI UPDATE METHOD - KEEP DISPLAY IN SYNC
        ==========================================
        
        🎯 PURPOSE: Update the click count display whenever count changes
        
        🧠 WHY SEPARATE METHOD?
        - Called from multiple places (onCreate, handleButtonClick)
        - Keeps UI update logic in one place
        - Easy to modify display format later
        - Follows DRY (Don't Repeat Yourself) principle
    */
    private void updateClickCountDisplay() {
        String countMessage = "Button clicked " + clickCount + " times";
        clickCountText.setText(countMessage);
        
        Log.d(TAG, "🔄 UI updated: " + countMessage);
    }
    
    /*
        🎉 USER FEEDBACK METHOD - MAKE THE APP FEEL ALIVE
        ================================================
        
        🎯 PURPOSE: Show different encouraging messages based on click count
        
        📱 TOAST EXPLANATION:
        Toast is a small popup message that appears briefly and disappears automatically.
        Perfect for showing quick feedback without interrupting user flow.
        
        🧠 UX (USER EXPERIENCE) PRINCIPLE:
        Good apps provide immediate feedback for user actions. Even a simple
        button click should feel responsive and engaging.
    */
    private void showEncouragingMessage() {
        String message;
        
        // Show different messages based on how many times they've clicked
        if (clickCount == 1) {
            message = "🎉 Great! You clicked the button!";
        } else if (clickCount <= 5) {
            message = "👍 Keep clicking! (" + clickCount + " clicks)";
        } else if (clickCount <= 15) {
            message = "🔥 You're on fire! " + clickCount + " clicks!";
        } else {
            message = "🏆 Amazing! " + clickCount + " clicks and counting!";
        }
        
        // Show the toast message to user
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        
        Log.d(TAG, "💬 Showed toast message: " + message);
    }
    
    /*
        🔄 ACTIVITY LIFECYCLE METHODS - UNDERSTANDING YOUR APP'S LIFE
        =============================================================
        
        🧠 WHY OVERRIDE THESE METHODS?
        Android can pause, stop, or destroy your Activity at any time to manage memory.
        By overriding these methods, you can:
        - Save user's work before losing focus
        - Release resources when not needed
        - Resume exactly where user left off
        - Handle device rotation gracefully
        
        📱 LIFECYCLE FLOW:
        onCreate() → onStart() → onResume() → [USER INTERACTION] → 
        onPause() → onStop() → onDestroy()
    */
    
    /*
        🚀 onStart() - ACTIVITY BECOMING VISIBLE
        ========================================
        
        📚 WHEN IS THIS CALLED?
        - After onCreate() when Activity is first created
        - When returning from another Activity
        - When user returns to app from background
        
        🎯 WHAT TO DO HERE:
        - Start animations
        - Register broadcast receivers  
        - Begin location updates
        - Start timers or background tasks
    */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "🚀 onStart() called - Activity is becoming visible to user");
    }
    
    /*
        ▶️ onResume() - ACTIVITY READY FOR INTERACTION
        ==============================================
        
        📚 WHEN IS THIS CALLED?  
        - After onStart() when Activity is first created
        - When returning from pause (phone call ended, notification dismissed)
        - When user switches back to your app
        
        🎯 WHAT TO DO HERE:
        - Resume animations or video playback
        - Resume camera preview
        - Start intensive CPU operations
        - Register sensors (accelerometer, GPS)
        
        💡 THIS IS WHERE USERS CAN INTERACT:
        Only when onResume() completes can users actually touch and interact with your app.
    */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "▶️ onResume() called - Activity ready for user interaction");
        Log.d(TAG, "📊 Current state: clickCount = " + clickCount);
    }
    
    /*
        ⏸️ onPause() - ACTIVITY LOSING FOCUS
        ====================================
        
        📚 WHEN IS THIS CALLED?
        - Another Activity comes to foreground (phone call, notification)
        - User opens another app
        - Screen turns off
        - User opens recent apps menu
        
        🎯 WHAT TO DO HERE:
        - Pause video/audio playback
        - Save user's current work (draft email, game progress)
        - Stop animations
        - Release camera resources
        
        ⚠️ IMPORTANT: This method should complete quickly!
        The new Activity can't show until your onPause() finishes.
    */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "⏸️ onPause() called - Activity losing focus");
        Log.d(TAG, "💾 Would save user data here in a real app");
    }
    
    /*
        ⏹️ onStop() - ACTIVITY NO LONGER VISIBLE
        ========================================
        
        📚 WHEN IS THIS CALLED?
        - User navigates to another Activity in your app
        - User switches to completely different app
        - User goes to home screen
        
        🎯 WHAT TO DO HERE:
        - Stop network requests
        - Unregister broadcast receivers
        - Release expensive resources
        - Stop background tasks
        
        💾 DATA PERSISTENCE:
        This is your last guaranteed chance to save important data.
        Android might kill your app after this without calling onDestroy().
    */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "⏹️ onStop() called - Activity no longer visible");
        Log.d(TAG, "🔌 Would stop network requests and background tasks here");
    }
    
    /*
        🔄 onRestart() - ACTIVITY COMING BACK TO LIFE
        =============================================
        
        📚 WHEN IS THIS CALLED?
        - When user returns to your Activity after it was stopped
        - Between onStop() and onStart()
        
        🎯 WHAT TO DO HERE:
        - Refresh data that might have changed while stopped
        - Restart services that were stopped
        - Update UI with latest information
    */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "🔄 onRestart() called - Activity restarting after being stopped");
    }
    
    /*
        💀 onDestroy() - ACTIVITY'S FINAL MOMENT
        ========================================
        
        📚 WHEN IS THIS CALLED?
        - User finishes Activity (presses back button)
        - System needs memory and kills your Activity
        - Device configuration changes (rotation)
        
        🎯 WHAT TO DO HERE:
        - Release ALL remaining resources
        - Unregister any remaining listeners
        - Clean up to prevent memory leaks
        - Final data save if not done already
        
        ⚠️ WARNING: This method might NOT be called!
        If system is low on memory, it might kill your app without calling onDestroy().
        Always save important data in onPause() or onStop().
    */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "💀 onDestroy() called - Activity is being destroyed");
        Log.d(TAG, "🧹 Final cleanup - releasing all resources");
        Log.d(TAG, "📊 Final click count was: " + clickCount);
    }
    
    /*
        📋 LIFECYCLE SUMMARY FOR BEGINNERS
        ==================================
        
        🔄 NORMAL FLOW:
        onCreate() → onStart() → onResume() → [User interacts] → 
        onPause() → onStop() → onDestroy()
        
        🔄 INTERRUPTED FLOW (Phone call, notification):
        onResume() → onPause() → [Interruption] → onResume()
        
        🔄 BACKGROUND/FOREGROUND:
        onResume() → onPause() → onStop() → [App in background] → 
        onRestart() → onStart() → onResume()
        
        💡 KEY PRINCIPLES:
        1. Save data in onPause() - it's the last guaranteed call
        2. Release resources in onStop() - app might stay stopped
        3. Don't do heavy work in onCreate() - it blocks UI
        4. Always call super.method() first in lifecycle methods
        
        🎯 PROFESSIONAL DEBUGGING:
        Watch the logcat output when using your app to see these methods
        being called. This understanding is crucial for Android development!
    */
}