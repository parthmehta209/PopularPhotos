# Project 1 - *Popular Photos*

**Popular Photos** is an android app that allows a user to check out popular photos from Instagram. The app utilizes Instagram API to display images and basic image information to the user.

Time spent: **6** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ] User can **scroll through current popular photos** from Instagram
* [ ] For each photo displayed, user can see the following details:
  * [ ] Graphic, Caption, Username
  * [ ] like count, user profile image

The following **optional** features are implemented:

* [ ] User can **pull-to-refresh** popular stream to get the latest popular photos
* [ ] Display each photo with the same style and proportions as the real Instagram
* [ ] Display each user profile image using a RoundedImageViewDisplay each user profile image using a [RoundedImageView](https://github.com/vinc3m1/RoundedImageView)
* [ ] Display a nice default placeholder graphic for each image during loading
* [ ] Improved the user interface through styling and coloring

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/KD57DZi.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

## Notes

The scrolling was very jerky and would cause the captions and user profile image to move when a new image is loaded. This was looking very bad. I tried to get arround this issue by setting 
the height and width of the imageview before I load an image in the the adapter. 

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [RoundedImageView] (https://github.com/vinc3m1/RoundedImageView) - Library to display a rounded image view

## License

    Copyright [2015] [Parth Mehta]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
