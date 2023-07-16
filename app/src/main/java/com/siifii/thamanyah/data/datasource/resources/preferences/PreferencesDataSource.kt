package com.siifii.thamanyah.data.datasource.resources.preferences


interface PreferencesDataSource {
    fun clearPrefs()

    fun logoutLocally()

    fun saveStringToPreferences(key: String, value: String?)

    fun saveStringToArrayStringsPreferences(key: String, value: String?)

    /**
     * Util method used to save int to preferences.
     *
     * @param key the key for setting the value.
     * @param value the saving value.
     */
    fun saveIntToPreferences(key: String, value: Int)


    fun saveFloatToPreferences(key: String, value: Float)


    /**
     * Util method used to clear value from preferences.
     *
     * @param key the key for clearing the value.
     */
    fun clearPreferenceValue(key: String)

    /**
     * Util method used to save boolean to preferences.
     *
     * @param key the key for setting the value.
     * @param value the saving value.
     */
    fun saveBooleanToPreferences(key: String, value: Boolean)

    /**
     * Util method used to delete string from preferences.
     *
     * @param value the deleting value.
     */
    fun deleteStringFromPreferences(value: String)

    /**
     * Util method used to get string from preferences.
     *
     * @param key the key for getting the value.
     *
     * @return the value.
     */
    fun getStringFromPreferences(key: String): String?

    fun getStringFromPreferences(key: String, default: String): String


    fun getStringSetFromPreferences(key: String): Set<String>

    fun deleteStringFromSetPreferences(key: String, value: String)

    /**
     * Util method used to get int from preferences.
     *
     * @param key the key for getting the value.
     *
     * @return the value.
     */
    fun getIntFromPreferences(key: String): Int?

    fun getIntFromPreferences(key: String, default: Int): Int

    fun getFloatFromPreferences(key: String, default: Float): Float

    /**
     * Util method used to get boolean from preferences.
     *
     * @param key the key for getting the value.
     *
     * @return the value.
     */
    fun getBooleanFromPreferences(key: String): Boolean

    fun <T> saveObject(key: String?, `object`: T)

    fun <T> getObject(key: String, classType: Class<T>?): T?

    fun <T> saveObjectsList(key: String?, objectList: List<T>?)

    fun <T> getObjectsList(key: String, classType: Class<T>?): List<T>?

    fun isKeyExists(key: String): Boolean

}