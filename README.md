# EventPracticeApp

A simple Android application that demonstrates user input handling and event management. This app allows users to enter their name and age, with proper validation and feedback mechanisms.

## Features

- **User Input Fields**: Two EditText components for name and age entry
- **Submit Button**: Processes user input when clicked
- **Input Validation**: Ensures all fields are filled and validates data format
- **Error Handling**: Displays appropriate error messages for invalid inputs
- **User Feedback**: Shows success/error messages via Toast notifications
- **Result Display**: Shows formatted output in a TextView

## Implementation Details

### Event Handling Implementation

The app uses `setOnClickListener()` to handle button clicks. The event handling is implemented in the `MainActivity.java` file with the following approach:

1. **View Initialization**: All UI components are initialized in the `initializeViews()` method
2. **Event Setup**: The submit button's click listener is set up in `setupEventHandling()`
3. **Click Handler**: The `handleSubmitButtonClick()` method processes the button click event
4. **Input Processing**: User input is retrieved, validated, and processed accordingly

### Techniques for Smooth and Stable Interaction

Several techniques were implemented to ensure smooth and stable user interaction:

1. **Input Validation**: 
   - Empty field checking with user-friendly error messages
   - Age range validation (0-150 years)
   - Name format validation (letters and spaces only)
   - Number format exception handling for age input

2. **Error Handling**:
   - Try-catch blocks for parsing operations
   - Comprehensive validation methods
   - Clear error messages displayed via Toast notifications

3. **User Feedback**:
   - Instant feedback through Toast messages
   - Success confirmation when data is submitted correctly
   - Clear result display in the TextView

4. **UI/UX Considerations**:
   - Clean, intuitive layout using ConstraintLayout
   - Proper input types (textPersonName for name, number for age)
   - Consistent styling and spacing
   - Clear visual feedback for user actions

## Future Improvements

For future versions of this app, the following improvements could be implemented:

1. **Enhanced Validation**:
   - Real-time input validation as the user types
   - More sophisticated name validation (handling international characters)
   - Date of birth input with age calculation

2. **Data Persistence**:
   - Save user data to local storage (SharedPreferences or SQLite)
   - Display previous entries in a list
   - Data export functionality

3. **UI/UX Enhancements**:
   - Material Design components for better visual appeal
   - Animation effects for better user experience
   - Dark mode support
   - Accessibility improvements (TalkBack support)

4. **Additional Features**:
   - Profile picture upload
   - Multiple user profiles
   - Data analytics and statistics
   - Settings screen for app configuration

5. **Performance Optimizations**:
   - Implement proper lifecycle management
   - Add loading states for better perceived performance
   - Optimize memory usage

## Screenshots

Screenshots demonstrating both correct and invalid input scenarios can be found in the `/activity4/` folder of this repository.

## Technical Stack

- **Language**: Java
- **UI Framework**: Android Views with ConstraintLayout
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Build Tool**: Gradle 8.0

