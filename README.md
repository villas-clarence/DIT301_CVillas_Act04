How did you implement event handling for user actions?

I implemented event handling by attaching a setOnClickListener() to the Submit button. When clicked, the app retrieved the input values from the Name and Age fields. Before showing the output, I validated that both fields were not empty. If any field was missing, a Toast message appeared, reminding the user to complete all fields. This approach made the interaction dynamic and responsive to user actions.

What techniques ensured smooth and stable interaction?

To maintain smooth and stable user interaction, I used input validation and a try-catch block to prevent crashes from invalid input, such as non-numeric age values. I also kept the layout clean and simple, using readable font sizes and appropriate spacing. Applying ConstraintLayout ensured the design stayed consistent and functional on various devices and orientations.

What improvements would you add in future versions?

For future improvements, I would make the app more interactive by adding real-time validation, input formatting, and a reset button to clear fields easily. I would also include customized feedback messages and enhance the interface with color themes and animations for better user engagement. Adding accessibility features like larger text options and screen reader compatibility would also make the app more inclusive.**
