package org.lshh.skeleton.library.resource.argument;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class ArgumentsHashMapTest {
    @Test
    public void testSizeWithEmptyMap() {
      ArgumentsHashMap<String, Integer> testMap = new ArgumentsHashMap<>();
      int size = testMap.size();
      assertEquals(0, size);
    }

    @Test
    public void testSizeWithNonEmptyMap() {
      ArgumentsHashMap<String, Integer> testMap = new ArgumentsHashMap<>();
      testMap.put("testKey1", 1);
      int size = testMap.size();
      assertEquals(1, size);
    }

    @Test
    public void testSizeAfterRemove() {
      ArgumentsHashMap<String, Integer> testMap = new ArgumentsHashMap<>();
      testMap.put("testKey1", 1);
      testMap.remove("testKey1");
      int size = testMap.size();
      assertEquals(0, size);
    }

    @Test
    void testIsEmptyWithEmptyMap() {
        ArgumentsHashMap<String, Integer> argumentsMap = new ArgumentsHashMap<>();
        assertTrue(argumentsMap.isEmpty());
    }

    @Test
    void testIsEmptyWithNonEmptyMap() {
        ArgumentsHashMap<String, Integer> argumentsMap = new ArgumentsHashMap<>();
        argumentsMap.put("test", 1);
        assertFalse(argumentsMap.isEmpty());
    }

    @Test
    void testIsEmptyAfterRemovingElements() {
        ArgumentsHashMap<String, Integer> argumentsMap = new ArgumentsHashMap<>();
        argumentsMap.put("test",1);
        argumentsMap.remove("test");
        assertTrue(argumentsMap.isEmpty());
    }

    @Test
    void testContainsKey_WhenKeyIsPresent_ReturnsTrue() {
        ArgumentsHashMap<Integer, String> map = new ArgumentsHashMap<>();
        map.put(1, "Value1");
        boolean result = map.containsKey(1);
        assertTrue(result);
    }

    @Test
    void testContainsKey_WhenKeyIsNotPresent_ReturnsFalse() {
        ArgumentsHashMap<Integer, String> map = new ArgumentsHashMap<>();
        map.put(1, "Value1");
        boolean result = map.containsKey(2);
        assertFalse(result);
    }

    @Test
    void testContainsKey_WhenMapIsEmpty_ReturnsFalse() {
        ArgumentsHashMap<Integer, String> map = new ArgumentsHashMap<>();
        boolean result = map.containsKey(1);
        assertFalse(result);
    }

    @Test
    public void testContainsValue_True() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        map.put("key", "value");
        assertTrue(map.containsValue("value"));
    }

    @Test
    public void testContainsValue_False() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        map.put("key", "value");
        assertFalse(map.containsValue("nonexistent"));
    }

    @Test
    public void testContainsValue_EmptyList() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        assertFalse(map.containsValue("value"));
    }

    @Test
    public void testContainsValue_List() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        map.put("key", List.of("value1", "value2", "value3"));
        assertTrue(map.containsValue("value2"));
    }

    @Test
    void getNullWhenNoKeyExists() {
        ArgumentsHashMap<String, String> argumentsHashMap = new ArgumentsHashMap<>();
        assertNull(argumentsHashMap.get("key"));
    }

    @Test
    void getNullWhenValueListIsEmpty() {
        ArgumentsHashMap<String, String> argumentsHashMap = new ArgumentsHashMap<>();
        argumentsHashMap.put("key", List.of());
        assertNull(argumentsHashMap.get("key"));
    }

    @Test
    void getValueWhenKeyExists() {
        ArgumentsHashMap<String, String> argumentsHashMap = new ArgumentsHashMap<>();
        argumentsHashMap.put("key", "value");
        assertEquals("value", argumentsHashMap.get("key"));
    }

    @Test
    public void testGetList_WhenKeyIsPresent() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        List<String> list = new ArrayList<>();
        list.add("Value1");
        list.add("Value2");
        map.put("TestKey", list);

        List<String> result = map.getList("TestKey");
        assertEquals(list, result);
    }

    @Test
    public void testGetList_WhenKeyIsNotPresent() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        List<String> result = map.getList("TestKey");
        assertNull(result);
    }

    @Test
    public void testPut() {
        ArgumentsHashMap<String, Integer> argumentsHashMap = new ArgumentsHashMap<>();
        Integer result = argumentsHashMap.put("Key1", 1);
        assertNotNull(result);
        assertEquals(1, result);
    }

    @Test
    void putShouldAddValueToList() {
        ArgumentsHashMap<String, Integer> argumentHashMap = new ArgumentsHashMap<>();
        int expectedValue = 10;
        argumentHashMap.put("Key1", expectedValue);
        assertEquals(expectedValue, argumentHashMap.get("Key1"));
    }

    @Test
    void putShouldReplaceOldValue() {
        ArgumentsHashMap<String, Integer> argumentHashMap = new ArgumentsHashMap<>();
        argumentHashMap.put("Key1", 10);
        int expectedValue = 20;
        argumentHashMap.put("Key1", expectedValue);
        assertEquals(expectedValue, argumentHashMap.get("Key1"));
    }

    @Test
    void putShouldReturnNullIfKeyIsNull() {
        ArgumentsHashMap<String, Integer> argumentHashMap = new ArgumentsHashMap<>();
        assertNull(argumentHashMap.put(null, 10));
    }

    @Test
    public void testAddNewEntry() {
        ArgumentsHashMap<String, Integer> argumentsHashMap = new ArgumentsHashMap<>();
        assertTrue(argumentsHashMap.add("TestKey", 123));
        assertTrue(argumentsHashMap.get("TestKey") == 123);
    }

    @Test
    public void testAddValueToExistingKey() {
        ArgumentsHashMap<String, Integer> argumentsHashMap = new ArgumentsHashMap<>();
        argumentsHashMap.put("TestKey", 123);
        assertTrue(argumentsHashMap.add("TestKey", 456));
        assertTrue(argumentsHashMap.getList("TestKey").size() == 2);
        assertTrue(argumentsHashMap.getList("TestKey").get(1) == 456);
    }

    @Test
    public void testAddAllWhenListIsEmpty() {
        ArgumentsHashMap<String, Integer> testMap = new ArgumentsHashMap<>();
        List<Integer> testList = new ArrayList<>();
        assertTrue(testMap.addAll("key", testList));
        assertArrayEquals(testList.toArray(), testMap.getList("key").toArray());
    }

    @Test
    public void testAddAllWhenListIsNotEmpty() {
        ArgumentsHashMap<String, Integer> testMap = new ArgumentsHashMap<>();
        List<Integer> testList = new ArrayList<>(List.of(1, 2, 3));
        assertTrue(testMap.addAll("key", testList));
        assertArrayEquals(testList.toArray(), testMap.getList("key").toArray());
    }

    @Test
    public void testAddAllWhenMapAlreadyContainsKey() {
        ArgumentsHashMap<String, Integer> testMap = new ArgumentsHashMap<>();
        testMap.add("key", 1);
        List<Integer> testList = new ArrayList<>(List.of(2, 3));
        assertTrue(testMap.addAll("key", testList));
        List<Integer> expectedList = new ArrayList<>(List.of(1, 2, 3));
        assertArrayEquals(expectedList.toArray(), testMap.getList("key").toArray());
    }

    @Test
    public void testRemoveNonexistentEntry() {
        ArgumentsHashMap<String, String> hashMapTest = new ArgumentsHashMap<>();

        String removedValue = hashMapTest.remove("NonexistentKey");

        assertNull(removedValue, "No value is expected as no corresponding entry was in the map.");
    }

    @Test
    public void testRemoveSingleValueEntry() {
        ArgumentsHashMap<String, String> hashMapTest = new ArgumentsHashMap<>();
        String testKey = "TestKey";
        String testValue = "TestValue";

        hashMapTest.put(testKey, testValue);

        String removedValue = hashMapTest.remove(testKey);

        assertEquals(testValue, removedValue, "Expected value was not returned on entry removal.");
        assertNull(hashMapTest.get(testKey), "After removal, no value should be associated with the key.");
    }

    @Test
    public void testRemoveMultiValueEntry() {
        ArgumentsHashMap<String, String> hashMapTest = new ArgumentsHashMap<>();
        List<String> testList = new ArrayList<>();
        String testKey = "TestKey";
        String testValue1 = "TestValue1";
        String testValue2 = "TestValue2";
        testList.add(testValue1);
        testList.add(testValue2);

        hashMapTest.put(testKey, testList);

        String removedValue = hashMapTest.remove(testKey);

        assertEquals(testValue1, removedValue, "Expected first value in the list was not returned on entry removal.");
        assertNull(hashMapTest.get(testKey), "After removal, no value should be associated with the key.");
    }

    @Test
    void testPutAll() {
        ArgumentsHashMap<String, String> argumentsHashMap = new ArgumentsHashMap<>();

        Map<String, String> testMap = new HashMap<>();
        testMap.put("Key1", "Value1");
        testMap.put("Key2", "Value2");
        testMap.put("Key3", "Value3");

        argumentsHashMap.putAll(testMap);

        assertEquals("Value1", argumentsHashMap.get("Key1"));
        assertEquals("Value2", argumentsHashMap.get("Key2"));
        assertEquals("Value3", argumentsHashMap.get("Key3"));
    }

    @Test
    void testPutAllWithEmptyMap() {
        ArgumentsHashMap<String, String> argumentsHashMap = new ArgumentsHashMap<>();

        Map<String, String> testMap = new HashMap<>();

        argumentsHashMap.putAll(testMap);

        assertEquals(0, argumentsHashMap.size());
    }

    @Test
    void testPutAllWithExistingKeys() {
        ArgumentsHashMap<String, String> argumentsHashMap = new ArgumentsHashMap<>();

        argumentsHashMap.put("Key1", "Value1");
        argumentsHashMap.put("Key2", "Value2");
        argumentsHashMap.put("Key3", "Value3");

        Map<String, String> testMap = new HashMap<>();
        testMap.put("Key1", "NewValue1");
        testMap.put("Key2", "NewValue2");
        testMap.put("Key3", "NewValue3");

        argumentsHashMap.putAll(testMap);

        assertEquals("NewValue1", argumentsHashMap.get("Key1"));
        assertEquals("NewValue2", argumentsHashMap.get("Key2"));
        assertEquals("NewValue3", argumentsHashMap.get("Key3"));
    }

    @Test
    void testClear() {
        ArgumentsHashMap<String, String> argumentsHashMap = new ArgumentsHashMap<>();

        argumentsHashMap.put("Key1", "Value1");
        argumentsHashMap.add("Key2", "Value2");
        argumentsHashMap.addAll("Key3", Arrays.asList("Value3_1", "Value3_2"));

        assertEquals(3, argumentsHashMap.size());

        argumentsHashMap.clear();

        assertTrue(argumentsHashMap.isEmpty());
    }

    @Test
    void testKeySet_noKeys() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        Set<String> keys = map.keySet();
        assertTrue(keys.isEmpty(), "Key set should be empty when no key-value pairs added.");
    }

    @Test
    void testKeySet_singleKeySingleValue() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        map.put("key1", "value1");
        Set<String> keys = map.keySet();
        assertEquals(1, keys.size(), "Should contain only one key when a single key-value pair is added.");
        assertTrue(keys.contains("key1"), "Should contain the key 'key1'.");
    }

    @Test
    void testKeySet_singleKeyMultipleValues() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        ArrayList<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");
        map.put("key1", values);
        Set<String> keys = map.keySet();
        assertEquals(1, keys.size(), "Should contain only one key when multiple values are added with a single key.");
        assertTrue(keys.contains("key1"), "Should contain the key 'key1'.");
    }

    @Test
    void testKeySet_multipleKeysMultipleValues() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        Set<String> keys = map.keySet();
        assertEquals(2, keys.size(), "Should contain two keys when two key-value pairs are added.");
        assertTrue(keys.contains("key1"), "Should contain the key 'key1'.");
        assertTrue(keys.contains("key2"), "Should contain the key 'key2'.");
    }

    @Test
    public void testValuesWhenMapIsEmpty() {
        ArgumentsHashMap<String, String> argsHM = new ArgumentsHashMap<>();
        assertEquals(0, argsHM.values().size());
    }

    @Test
    public void testValuesWhenMapHasSingleKeyValue() {
        ArgumentsHashMap<String, String> argsHM = new ArgumentsHashMap<>();
        argsHM.put("TestKey", "TestValue");
        List<String> expected = Arrays.asList("TestValue");
        assertEquals(expected, argsHM.values());
    }

    @Test
    public void testValuesWhenMapHasMultipleKeysAndValues() {
        ArgumentsHashMap<String, String> argsHM = new ArgumentsHashMap<>();
        argsHM.put("TestKey1", "TestValue1");
        argsHM.put("TestKey2", "TestValue2");
        argsHM.put("TestKey3", "TestValue3");
        List<String> expected = Arrays.asList("TestValue1", "TestValue2", "TestValue3");
        expected.forEach(value -> assertTrue(argsHM.values().contains(value)));
    }

    @Test
    void whenValueListsCalled_thenCorrectCollectionReturned() {
        ArgumentsHashMap<String, String> testMap = new ArgumentsHashMap<>();
        testMap.put("k1", "v1");
        testMap.put("k2", "v2");
        testMap.add("k3", "v3");

        Collection<List<String>> values = testMap.valueLists();

        assertEquals(3, values.size());

        testMap.add("k1", "v11");

        values = testMap.valueLists();
        assertEquals(3, values.size());

        assertEquals(2, testMap.getList("k1").size());
        assertTrue(testMap.getList("k1").contains("v1"));
        assertTrue(testMap.getList("k1").contains("v11"));
    }

    @Test
    void testEntrySet_emptyMap() {
        ArgumentsHashMap<Integer, String> argumentsHashMap = new ArgumentsHashMap<>();

        assertNull(argumentsHashMap.entrySet(), "Entry set of an empty map must be null.");
    }

    @Test
    void testEntrySet_nonEmptyMap() {
        ArgumentsHashMap<String, Integer> argumentsHashMap = new ArgumentsHashMap<>();
        String key = "Key1";
        Integer value = 200;
        argumentsHashMap.put(key, value);

        Set<Map.Entry<String, Integer>> entrySet = argumentsHashMap.entrySet();

        assertNotNull(entrySet, "Entry set of a non-empty map must not be null.");
        assertEquals(1, entrySet.size(), "Entry set size must match the number of unique keys added in map.");
        Map.Entry<String, Integer> entry = entrySet.iterator().next();
        assertEquals(key, entry.getKey(), "Map's key does not match expected key.");
        assertEquals(value, entry.getValue(), "Map's value (first from the list) does not match expected value.");
    }

    @Test
    void testEntrySet_multiValueKeyMap() {
        ArgumentsHashMap<String, Integer> argumentsHashMap = new ArgumentsHashMap<>();
        String key = "Key1";
        List<Integer> values = List.of(100, 200, 300);
        argumentsHashMap.put(key, values);

        Set<Map.Entry<String, Integer>> entrySet = argumentsHashMap.entrySet();

        assertNotNull(entrySet, "Entry set of a non-empty map must not be null.");
        assertEquals(1, entrySet.size(), "Entry set size must match the number of unique keys added in map.");
        Map.Entry<String, Integer> entry = entrySet.iterator().next();
        assertEquals(key, entry.getKey(), "Map's key does not match expected key.");
        assertEquals(values.get(0), entry.getValue(), "Map's value (first from the list) does not match expected value.");
    }

    @Test
    public void testEntryListSetWhenMapIsEmpty() {
        ArgumentsHashMap<String, Integer> argumentsHashMap = new ArgumentsHashMap<>();
        assertTrue(argumentsHashMap.entryListSet().isEmpty());
    }

    @Test
    public void testEntryListSetWhenMapHasOneEntry() {
        ArgumentsHashMap<String, Integer> argumentsHashMap = new ArgumentsHashMap<>();
        argumentsHashMap.add("testKey", 1);
        Map.Entry<String, List<Integer>> expectedEntry = Map.entry("testKey", List.of(1));
        Set<Map.Entry<String, List<Integer>>> expectedResult = Set.of(expectedEntry);

        assertEquals(expectedResult, argumentsHashMap.entryListSet());
    }

    @Test
    public void testEntryListSetWhenMapHasMultipleEntries() {
        ArgumentsHashMap<String, Integer> argumentsHashMap = new ArgumentsHashMap<>();
        argumentsHashMap.add("testKey1", 1);
        argumentsHashMap.add("testKey1", 2);
        argumentsHashMap.add("testKey2", 3);
        Map.Entry<String, List<Integer>> expectedEntry1 = Map.entry("testKey1", List.of(1, 2));
        Map.Entry<String, List<Integer>> expectedEntry2 = Map.entry("testKey2", List.of(3));
        Set<Map.Entry<String, List<Integer>>> expectedResult = Set.of(expectedEntry1, expectedEntry2);

        assertEquals(expectedResult, argumentsHashMap.entryListSet());
    }

    @Test
    public void testEquals() {
        ArgumentsHashMap<String, Integer> ahm1 = new ArgumentsHashMap<>();
        ahm1.put("One", 1);

        assertTrue(ahm1.equals(ahm1));
    }

    @Test
    public void testNotEqualsNotValid() {
        ArgumentsHashMap<String, Integer> ahm1 = new ArgumentsHashMap<>();

        assertFalse(ahm1.equals("not valid"));
    }

    @Test
    public void testNotEqualsDifferentSize() {
        ArgumentsHashMap<String, Integer> ahm1 = new ArgumentsHashMap<>();
        ahm1.put("One", 1);

        ArgumentsHashMap<String, Integer> ahm2 = new ArgumentsHashMap<>();
        ahm2.put("One", 1);
        ahm2.put("Two", 2);

        assertFalse(ahm1.equals(ahm2));
    }

    @Test
    public void testNotEqualsDifferentEntries() {
        ArgumentsHashMap<String, Integer> ahm1 = new ArgumentsHashMap<>();
        ahm1.put("One", 1);

        ArgumentsHashMap<String, Integer> ahm2 = new ArgumentsHashMap<>();
        ahm2.put("One", 2);

        assertFalse(ahm1.equals(ahm2));
    }

    @Test
    public void testEqualsWithNullValue() {
        ArgumentsHashMap<String, Object> ahm1 = new ArgumentsHashMap<>();
        ahm1.put("One", null);

        ArgumentsHashMap<String, Object> ahm2 = new ArgumentsHashMap<>();
        ahm2.put("One", null);

        assertTrue(ahm1.equals(ahm2));
    }

    @Test
    public void testNotEqualsWithNullValue() {
        ArgumentsHashMap<String, Object> ahm1 = new ArgumentsHashMap<>();
        ahm1.put("One", null);

        ArgumentsHashMap<String, Object> ahm2 = new ArgumentsHashMap<>();
        ahm2.put("One", 1);

        assertFalse(ahm1.equals(ahm2));
    }

    @Test
    public void testEqualsWithMultipleValues() {
        ArgumentsHashMap<String, Integer> ahm1 = new ArgumentsHashMap<>();
        ahm1.put("Key", Arrays.asList(1, 2, 3));

        ArgumentsHashMap<String, Integer> ahm2 = new ArgumentsHashMap<>();
        ahm2.put("Key", Arrays.asList(1, 2, 3));

        assertTrue(ahm1.equals(ahm2));
    }

    @Test
    public void testNotEqualsWithMultipleValues() {
        ArgumentsHashMap<String, Integer> ahm1 = new ArgumentsHashMap<>();
        ahm1.put("Key", Arrays.asList(1, 2, 3));

        ArgumentsHashMap<String, Integer> ahm2 = new ArgumentsHashMap<>();
        ahm2.put("Key", Arrays.asList(1, 2, 4));

        assertFalse(ahm1.equals(ahm2));
    }

    @Test
    void testHashCodeForEmptyMap() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        assertEquals(0, map.hashCode());
    }

    @Test
    void testHashCodeForSingleEntry() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        map.put("Key1", List.of("Value1"));
        int expectedHash = Map.entry("Key1", List.of("Value1")).hashCode();
        assertEquals(expectedHash, map.hashCode());
    }

    @Test
    void testHashCodeForMultipleEntries() {
        ArgumentsHashMap<String, String> map = new ArgumentsHashMap<>();
        map.put("Key1", List.of("Value1"));
        map.put("Key2", List.of("Value2"));
        map.put("Key3", List.of("Value3"));

        int expectedHash = Map.entry("Key1", List.of("Value1")).hashCode()
                + Map.entry("Key2", List.of("Value2")).hashCode()
                + Map.entry("Key3", List.of("Value3")).hashCode();

        assertEquals(expectedHash, map.hashCode());
    }
}