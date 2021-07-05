# Mobile_App_Dev_Project



### Overview

App contains inbuilt online voting and unit conversion features.Unit conversion is directly done in client side whereas online voting requires backend database to be in cloud, Firebase real-time database is used for online voting system.

### Application activity flow

![app_flow](https://user-images.githubusercontent.com/49629253/114813995-e3784300-9dd0-11eb-802c-944bfb2bb7ea.png)


### Database

![image](https://user-images.githubusercontent.com/49629253/114823216-d2373280-9de0-11eb-89bd-0a4116a535de.png)

###### Admin_key
Used to authenticate admin so that only members with admin-key can register for new admins
###### Admins
Contains all admins in the form of user class with mail as their unique key.
###### Contestants
Contains all contestants in voting. This is a list of contestant class with name as their unique key.
###### Users
Contains all users registered by admin for the app. This is a list of user class with mail as their unique key.
###### voted
This is a list of users who already voted so they cant be voted again





### Screenshots


![IMG_20210415_100716](https://user-images.githubusercontent.com/49629253/114822998-84222f00-9de0-11eb-9502-1e915b72d1ba.jpg)
![IMG_20210415_100730](https://user-images.githubusercontent.com/49629253/114823087-a74cde80-9de0-11eb-9414-9dccdb33af69.jpg)
![IMG_20210415_100743](https://user-images.githubusercontent.com/49629253/114823115-b2a00a00-9de0-11eb-96ea-4227148d97ab.jpg)




### External libraries used:

[MPAndroid Chart](https://github.com/PhilJay/MPAndroidChart)

[Parceler](https://github.com/johncarl81/parceler)


### Further improvements that can be done

Admin section can be separated to another package to avoid confusion.

More methods can be added to admin section so as to get more command over database so as to avoid managing database in backend.

Voting activity can be extended to have an web-mail authentication followed by a selfie option to tighten the security measures as online voting is prone to frauds.
