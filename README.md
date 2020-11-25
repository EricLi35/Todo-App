# Eric's To-Do List

## Alleviate Stress From Your Life!

Life can get difficult sometimes, and especially during this period in human history 
where a lot of everyday activity is spent online, it's important to stay on track with 
upcoming deadlines.\
\
With everything being virtually online, it could feel lonely at times due to the decrease in human interaction. 
No need to worry however, this handy app will display messages based on levels of productivity and provide feedback on your progress!
The main purpose of the To-Do List is to keep track of tasks that need to be completed. This allows individuals to stay afloat of deadlines 
and remain organized in this unprecendented period in history.   
\
The great thing about this app is that it could be used by pretty much anyone. It's design and functionality are very intuitive, therefore allowing it to 
integrate seamlessly into one's daily workflow.\
\
With that being said, students and young adults would benefit the most from this app, simply because their daily lives are just so busy.
Assignments, quizzes, labs, midterms, tutorials...I could go on! Their schedule is extremely busy and almost something new pops up every day,
so hopefully this app can help them keep track of their due dates and remain on schedule.\
\
This To-Do List is of interest to me because I actually currently use an app similar to the one I’m trying to design. It’s called 
*Focus To-Do Pomodoro Technique & Tasks*. While I’m satisfied with most of the functionality that this app has to offer, there are some features that I wish
could be added. Most notably, I’m looking for an app with the ability to provide feedback on the levels of productivity, depending on how many tasks are 
left on the list. Additionally, a motivational quote should be displayed on screen every time a new task is added to the list. Instead of contacting the 
developers of the current app I’m using, I decided to just create my own To-Do List application.

## User Stories
- As a user, I want to be able to add tasks to a to-do list.
- As a user, I want to be able to toggle tasks from being incomplete to complete, and complete to incomplete if necessary.
- As a user, I want to be able to remove tasks (regardless of completion) off the to-do list.
- As a user, I want to be able to view the to-do list and see what tasks are left.
- As a user, I want to be able to save my to-do list to file.
- As a user, I want to be able to load my to-do list from file.
- As a user, I want to be able to add multiple tasks to a todo list
- As a user, I want to be able to load and save the state of the application
- As a user, I want to be able to play a sound when a new task is added

Phase 4: Task 2
I chose to implement the Map interface somewhere in my code. The specific class where the Map interface was used is the ToDoApp class.

Phase 4: Task 3
One of the first things that jumped out at me when looking at this UML class design diagram was that some unnecessary coupling is present.
-The association between ToDoApp and Task is unnecessary, so that arrow could be removed.