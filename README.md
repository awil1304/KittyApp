# KittyApp
T - Kitty App Challange

Hi guys,

This challenge was fun and I learned plenty and gained a lot of experience with Kotlin.

Below, I am talking about what I have implemented successfully and about what I have struggled with.

Successfully implemented challenge points:
1. General (must-have)
  1.1 Kotlin 
  1.2 minSdkVersion 22 
  1.3 MVVM architecture 
  
2. Features
  2.1 The images are arranged vertically with the corresponding text in "cards" 2.2 The images each have a loading and an error placeholder
  2.3 A ProgressBar is shown while the JSON data is being loaded
  2.4 In case of an error a SnackBar featuring a “Retry” option is shown
  
Optional / Bonus:
  - In landscape or on tablet devices there is more than one column in the list
  - The rearrangement (2.5) is animated
  - A dependency injection framework of your choice is used - yes
  - If you have any creative ideas and want to add other features that you think would make the app even
    better, feel free to do so


Struggles:

Aside from the distraction due to a personal tragedy this week which cost me quite some time and unfortunately focus as well,
I struggled with the following tasks:

2.5 If a card is clicked, it moves to the first position

      Among other things, I learned the basics of Dagger-Hilt for this project.
      Since with Dagger-Hilt I have never had to initialize the actual list of objects from the API, I had trouble getting the index
      on click and thus removing and adding it at list[0]. Having read that initializing the list manually is bad practice, because of the implications
      it would have for testing, I refrained from doing so.
      Maybe copying it for the State as a mutable list and then loading it into the composables would have been the solution,
      however, I ran out of time to try. With the right implementation, this would have reduced the number of API calls upon opening the app as well.
      
2.6 The new arrangement from 2.5 persists after an orientation change

      Follow-up problem from 2.5
      
1.4 The most important parts of the business logic (ViewModels, Repositories, Use-Cases...) are unit-tested

     I have not learned unit testing yet. Due to the lack of time mentioned above, I couldn't get into it until now.
     Trying to test the KittyRepository, I thought for testing I could initialize the list manually, however, I encountered a problem
     with implementing the interface.
     
I will try to get the app fully functional in the upcoming days, however, I have to hand it in now, because I have to travel to Bavaria for the weekend
due to the tragedy mentioned above.

I am looking forward to hearing from you soon.

Cheers,

Andreas
