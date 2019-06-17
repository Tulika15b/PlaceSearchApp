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

6. UI Components :


*MainScreen* : It uses a SearchView widget for typing in and searching the query
*SearchedResultsFragment* : It makes use of RecyclerView, RecyclerView Adapter(PlaceSearchListAdapter), RecyclerView ItemViewHolder(PlaceSearchItemViewHolder) to show the list of Venues received from the controller after the http request. It also has an option to invoke the FullScreenMapActivity using the FloatingActionButton to list all the venues markers on map
*FullScreenMapsActivity* : It uses the SupportMapFragment to invoke the Google Map related APIs
*PlaceDetailsActivity* : It uses a collapsing toolbar layout, and contains the further details of a selected venue. It also hosts a static map view of the venue.


# Additional Libraries used
The App makes use of the Picasso library to fetch the images from the icon url.

# Enhancements Required
1. The App can be modified to support ViewModels
2. Currently the (optional requirement)logic to add and retrieve the favorite places is not completed and can be added.
3. Instead of using Picasso library, we can create a DownloadImageAsyncTask to handle the image fetch.

