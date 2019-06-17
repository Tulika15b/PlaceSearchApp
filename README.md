# PlaceSearchApp
App to search places in Seattle; uses FourSquare API

PlaceSearchApp helpd the user looking for say "Coffee" places or "Dr. centers" find the places in and around Seattle.
The App makes use of the Foursquare API for retriving the place related data.

# Code Architecture

The code follows a MVC pattern:
1. Data Model Classes : 
   Venue - This is the model class for a Place or Venue, which is retrieved and stored from venue search API.
   VenueLocation - Venue Location holds the additional data related to the Venue
   VenueCategory - Venue category holds the information of the categories, while looking for a primary category for the iconUrl.
   
2. Controller class : PlaceSearchController
PlaceSearchController class is responsible for taking in the search query and forwarding it to the network or Http request handling class block. It also takes care of sending that response back to the UI related or view classes.

3. View Classes :
The App uses a Activity, Fragment Model. It has a MainActivity, which further adds SearchFragment(First screen) and SearchResultsFragment(Second screen with search responses). We also have the FullScreenMapActivity(for the optional screen) and the PlaceDetailsActivity(uses the collapsing toolbar layout).

4. Utility classes : PlaceSearchCommonUtils(contains the common code required at many places in the app), PlaceSearchConstants(contains all the constants strings), PlaceSearcherJsonParser(parser class to parse the Foursquare API response to a list of Venue object). APIResponseCallback(callback listener interface)

5. Network component : The app makes use of a combination of JobIntentService and RequestReceiver classes of Android. The JobIntentService class i.e APIRequestIntentService is responsible for initiating the Http request and receiving the responses for the same. Since the intentService do not have a provision inherently to send back the result, we make use of ResultReceiver class. It is basically a callback mechanism to send back the response to the suitable handler classes.

