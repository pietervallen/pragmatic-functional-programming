package com.for_comprehension.stream;

import com.for_comprehension.stream.domain.dataset.Store;
import com.for_comprehension.stream.domain.domain.Customer;
import com.for_comprehension.stream.domain.domain.Item;
import com.for_comprehension.stream.domain.domain.ItemEntry;
import com.for_comprehension.stream.domain.domain.Shop;
import org.assertj.core.data.Offset;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.function.Predicate.isEqual;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class Exercises extends Store {

    private final List<Customer> customerList = getMall().getCustomerList();
    private final List<Shop> shopList = getMall().getShopList();

    /**
     * Find names of customers with budgets over 10_000
     */
    @Test
    public void P1_hugeBudgets() {
        // when
        List<String> result = null;

        // then
        assertThat(result)
          .hasSize(2)
          .contains("Diana", "Andrew");
    }

    /**
     * Find average age of all customers
     * {@link Collectors#summarizingInt(ToIntFunction)}
     */
    @Test
    public void P02_howOldAreTheCustomers() {
        // when
        double average = 0;

        // then
        assertThat(average)
          .isEqualTo(28.7, Offset.offset(0.1d));
    }

    /**
     * Create a list of customer's ascending ordered age values.
     */
    @Test
    public void P03_sortByAge() {

        // when
        final List<Integer> result  = null;

        // then
        assertThat(result)
          .contains(21, 22, 26, 27, 28, 32, 35, 36, 38);
    }

    /**
     * Create a list of customer's descending ordered age values.
     */
    @Test
    public void P04_descSortByAge() {

        // when
        final List<Integer> result = null;

        // then
        assertThat(result)
          .contains(38, 36, 35, 32, 28, 27, 26, 22, 22, 21);
    }

    /**
     * Find names of all items customers want to buy
     */
    @Test
    public void P05_itemsCustomersWantToBuy() {
        // when
        final Set<String> result = null;


        // then
        assertThat(result)
          .contains("small table", "plate", "fork", "screwdriver", "cable", "earphone", "onion",
          "ice cream", "crisps", "chopsticks", "cable", "speaker", "headphone", "saw", "bond",
          "plane", "bag", "cold medicine", "chair", "desk", "pants", "coat", "cup", "plate", "fork",
          "spoon", "ointment", "poultice", "spinach", "ginseng", "onion");
    }

    /**
     * Find what does the youngest customer want to buy.
     * If the youngest customer does not exist, return an empty list.
     */
    @Test
    public void P06_youngestCustomer() {

        // when
        final List<ItemEntry> result = null;

        // then
        assertThat(result)
          .extracting(ItemEntry::getName)
          .containsOnly("fork", "cup", "plate", "spoon");
    }

    /**
     * Find whether any customer older than 40 exists or not.
     */
    @Test
    public void P07_isThereAnyoneOlderThan40() {
        // when
        boolean result = true;

        // then
        assertThat(result).isFalse();
    }

    /**
     * Check whether all customer are older than 20 or not
     */
    @Test
    public void P08_isEverybodyOlderThan20() {
        // when
        boolean result = false;

        // then
        assertThat(result).isTrue();
    }

    /**
     * Confirm that all customers have at least one elements on their want-to-buy list
     */
    @Test
    public void P09_everyoneWantsSomething() {
        // when
        boolean result = false;

        // then
        assertThat(result).isTrue();
    }

    /**
     * Create a list of customer names (no duplicates)
     */
    @Test
    public void P10_nameList() {
        // when
        final List<String> result = null;

        // then
        assertThat(result).containsOnly("Joe", "Steven", "Patrick", "Diana", "Chris", "Kathy", "Alice", "Andrew",
          "Martin", "Amy");
    }

    /**
     * Create a set of customer ages
     */
    @Test
    public void P11_ageSet() {
        // when
        final Set<Integer> result = null;

        // then
        assertThat(result).hasSize(9);
        assertThat(result).containsOnly(21, 22, 26, 27, 28, 32, 35, 36, 38);
    }

    /**
     * Create a csv string of customer names in brackets "[]"
     */
    @Test
    public void P12_nameInCsv() {
        // when
        final String result = null;

        // then
        assertThat(result).isEqualTo("[Joe,Steven,Patrick,Diana,Alice,Andrew,Martin,Amy,Chris,Kathy]");
    }

    /**
     * Create a map of age as key and number of customers as value
     */
    @Test
    public void P13_ageDistribution() {
        // when
        final Map<Integer, Long> result = null;

        // then
        assertThat(result)
          .hasSize(9)
          .containsEntry(22, 2L)
          .containsValues(1L, 2L);
    }

    /**
     * Calculate the average customer's age
     */
    @Test
    public void P14_averageAge() {
        // when
        final double result = 0d;


        // then
        assertThat(result).isEqualTo(28.7);
    }

    /**
     * Calculate the sum of all items' prices
     */
    @Test
    public void P15_howMuchToBuyAllItems() {
        // when
        long result = 0;

        // then
        assertThat(result).isEqualTo(60930L);
    }

    /**
     * Create a set of item names that customers want to buy, but are not available anywhere
     */
    @Test
    public void P16_itemsNotOnSale() {

        // when


        final Set<String> result = null;

        // then
        assertThat(result).hasSize(3);
        assertThat(result).containsOnly("bag", "pants", "coat");
    }

    /**
     * Create a customer's name list including who can bue everything they want.
     * All items must be in stock
     */
    @Test
    public void P17_havingEnoughMoney() {
        // when

        final List<String> customerNameList = null;

        // then
        assertThat(customerNameList).hasSize(8);
        assertThat(customerNameList).containsOnly("Joe", "Steven", "Patrick", "Diana", "Kathy", "Alice", "Martin", "Amy");
    }

}
