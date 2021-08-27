# gnani-voice-biometric-armour365
Gnani Voice Biometric Android SDK

[![](https://jitpack.io/v/gnani-ai/gnani-voice-biometric-armour365.svg)](https://jitpack.io/#gnani-ai/gnani-voice-biometric-armour365)

# Step 1. Add it in your root build.gradle at the end of repositories:



	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


# Step 2. Add the dependency


	
	dependencies{
		implementation 'com.github.gnani-ai:gnani-voice-biometric-armour365:1.0.0'
	}
	

## Implement VoiceResultListener and override methods

	
	override fun success(message: String) {
		Log.d(TAG, "success: $message")
	}

	override fun failed(message: String) {
		Log.d(TAG, "failed: $message")
	}

#
	val gnaniClient = GnaniClient("TOKEN", "ACCESSKEY", "PRODUCT", this)
	

that's it for creating a client.

	UID(String) -> a unique identifier for the user(can be phone number, email id or anything)
	file(File) -> audio file


* Enrollment

		gnaniClient.enrollUser("UID", file)

* Authentication

		gnaniClient.authenticateUser("UID", file)

* Deleting a user

		gnaniClient.disEnrollUser("UID")

Check out the sample app for usage refernces. 
