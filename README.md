# gnani-voice-biometric-armour365
Gnani Voice Biometric Android SDK

[![](https://jitpack.io/v/gnani-ai/gnani-voice-biometric-armour365.svg)](https://jitpack.io/#gnani-ai/gnani-voice-biometric-armour365)

#Step 1. Add it in your root build.gradle at the end of repositories:

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
#Step 2. Add the dependency
dependencies {
	        implementation 'com.github.gnani-ai:gnani-voice-biometric-armour365:1.0.0'
}

GnaniClient gnaniClient = new GnaniClient("TOKEN", "ACCESSKEY", "PRODUCT", this);

that's it for creating a client.

UID(String) -> a unique identifier for the user(can be phone number, email id or anything)
file -> audio file

for enrollment,
gnaniClient.enrollUser("UID", file)

for authentication,
gnaniClient.authenticateUser("UID", file)

for deleting a user,
gnaniClient.disEnrollUser("UID")

Check out the sample app for usage refernces. 
