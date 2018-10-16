/*
 * Copyright (C) 2018 Juan J. Martínez
 * 
 * All rights reserved. This complete software or any portion thereof
 * can be used as reference but may not be reproduced in any manner 
 * whatsoever without the express written permission of the owner.
 * 
 * The purpose of this is to be consulted and used as a referece of 
 * functionallyty.
 * 
 * Developed in Mexico City
 * First version, 2018
 *
 */
/**
 *
 * @author Juan J. Martínez
 * @email juanjmtzs@gmail.com
 * @phone +52-1-55-1247-8044
 * @linkedin https://www.linkedin.com/in/juanjmtzs/
 *
 */
package Decoder;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class BMVNoBytes implements Potocol {

    public static int count_depth = 0;
    public static int count_probable_allocation_price = 0;
    public static int count_continuos_auction_beginning = 0;
    public static int count_status_changes = 0;
    public static int count_to_middle_price_existing_quotes = 0;
    public static int count_local_and_global_stock_markets = 0;
    public static int count_debt_metals_and_money_market = 0;
    public static int count_capital_market_warrants = 0;
    public static int count_capital_market_TRACS = 0;
    public static int count_mutual_funds = 0;
    public static int count_underlying_value_on_warrants = 0;
    public static int count_tradability = 0;
    public static int count_trade_cancellation = 0;
    public static int count_weighted_average_price_settlement_prices = 0;
    public static int count_best_offer = 0;
    public static int count_capital_trades = 0;
    public static int count_event_systems = 0;
    public static int count_virtual_trades = 0;
    public static int count_mutual_fund_trades = 0;
    public static int count_registry_operations = 0;

    public static int count_derivatives_market_trades = 0;
    public static int count_order_addition = 0;
    public static int count_order_change = 0;
    public static int count_execution_of_orders = 0;
    public static int count_volume_update = 0;
    public static int count_orders_cancellation = 0;
    public static int count_instruments_statistics = 0;
    public static int count_inavs = 0;
    public static int count_general_indexes = 0;
    public static int count_indexes_samples = 0;
    public static int count_open_interest = 0;
    public static int count_public_offerings = 0;
    public static int count_futures_operations_and_swaps_derivatives_market = 0;
    public static int count_derivatives_market_strategies = 0;
    public static int count_dollar_buy_sell = 0;
    public static int count_short_sales_balances_per_instrument = 0;
    public static int count_capital_markets_multiples = 0;
    public static int count_benchmarks = 0;
    public static int count_capitalization_rules = 0;
    public static int count_origin_closing_price = 0;

    public static int count_consolidated_relation_between_bmvbiva_instruments = 0;
    public static int count_consolidated_system_events = 0;
    public static int count_consolidated_capitals_market_trades = 0;
    public static int count_consolidated_trades_cancellation = 0;
    public static int count_consolidated_best_bid = 0;
    public static int count_consolidated_registration_of_order = 0;
    public static int count_consolidated_execution_of_orders = 0;
    public static int count_consolidated_cancellation_of_orders = 0;
    public static int count_consolidated_amendment_of_orders = 0;
    public static int count_consolidated_updating_of_volume = 0;
    public static int count_consolidated_changes_in_status = 0;
    public static int count_consolidated_reference_price = 0;
    public static int count_consolidated_probable_price_of_allotment = 0;
    public static int count_consolidated_startup_of_ongoing_bidding_sessions = 0;
    public static int count_consolidated_existence_of_bids_at_mean_price = 0;
    public static int count_consolidated_weighted_average_price_ppp = 0;
    public static int count_consolidated_investment_companies_trades = 0;
    public static int count_consolidated_inavs = 0;
    public static int count_consolidated_probable_price_of_allotment_volume_indicator = 0;
    public static int count_consolidated_spread_quality = 0;
    public static int count_consolidated_message_ratio = 0;
    public static int count_consolidated_depth_at_best_prices = 0;
    public static int count_consolidated_effective_spread = 0;
    public static int count_consolidated_trading_sniper = 0;
    public static int count_consolidated_quotes_quality = 0;
    public static int count_consolidated_price_leaderboard = 0;
    public static int count_consolidated_spread = 0;
    public static int count_consolidated_benchmark_trade = 0;
    public static int count_consolidated_hour_tracker = 0;
    public static int count_consolidated_big_picture = 0;
    public static int count_consolidated_local_and_global_stock_market = 0;
    public static int count_consolidated_capital_market_warrants = 0;
    public static int count_consolidated_capital_market_tracs = 0;
    public static int count_consolidated_mutual_funds = 0;
    public static int count_consolidated_debt_and_metals_market = 0;

    public static int count_global_and_local_catalog = 0;
    public static int count_turn_over_ratio_per_security = 0;
    public static int count_securities_suspension = 0;
    public static int count_securities_unsuspension = 0;
    public static int count_negotiation_state_change_SUBRM = 0;
    public static int count_official_closing_price = 0;

    static final byte depth = '1';
    static final byte probable_allocation_price = '2';
    static final byte continuos_auction_beginning = '3';
    static final byte status_changes = '4';
    static final byte to_middle_price_existing_quotes = '5';
    static final byte local_and_global_stock_markets = 'a';
    static final byte debt_metals_and_money_market = 'b';
    static final byte capital_market_warrants = 'c';
    static final byte capital_market_TRACS = 'e';
    static final byte mutual_funds = 'f';
    static final byte underlying_value_on_warrants = 'y';
    static final byte tradability = 'E';
    static final byte trade_cancellation = 'H';
    static final byte weighted_average_price_settlement_prices = 'M';
    static final byte best_offer = 'O';
    static final byte capital_trades = 'P';
    static final byte event_systems = 'S';
    static final byte virtual_trades = 'V';
    static final byte mutual_fund_trades = 'Y';
    static final byte registry_operations = 'Z';
    static final byte origin_closing_price = '~';

    static final byte derivatives_market_trades = 'Q';
    static final byte order_addition = 'A';
    static final byte order_change = 'F';
    static final byte execution_of_orders = 'C';
    static final byte volume_update = 'X';
    static final byte orders_cancellation = 'D';
    static final byte instruments_statistics = 'R';
    static final byte inavs = 'G';
    static final byte general_indexes = 'U';
    static final byte indexes_samples = 'W';
    static final byte open_interest = 'I';
    static final byte public_offerings = 'B';
    static final byte futures_operations_and_swaps_derivatives_market = 'd';
    static final byte derivatives_market_strategies = 'g';
    static final byte dollar_buy_sell = 'r';
    static final byte short_sales_balances_per_instrument = 's';
    static final byte capital_markets_multiples = 't';
    static final byte benchmarks = 'x';
    static final byte capitalization_rules = 'z';

    static final byte consolidated_relation_between_bmvbiva_instruments = 'j';
    static final byte consolidated_system_events = '7';
    static final byte consolidated_capitals_market_trades = 'p';
    static final byte consolidated_trades_cancellation = 'q';
    static final byte consolidated_best_bid = 'm';
    static final byte consolidated_registration_of_order = 'n';
    static final byte consolidated_execution_of_orders = 'k';
    static final byte consolidated_cancellation_of_orders = 'u';
    static final byte consolidated_amendment_of_orders = 'v';
    static final byte consolidated_updating_of_volume = 'w';
    static final byte consolidated_changes_in_status = '9';
    static final byte consolidated_reference_price = '8';
    static final byte consolidated_probable_price_of_allotment = 'i';
    static final byte consolidated_startup_of_ongoing_bidding_sessions = ')';
    static final byte consolidated_existence_of_bids_at_mean_price = ',';
    static final byte consolidated_weighted_average_price_ppp = '6';
    static final byte consolidated_investment_companies_trades = '(';
    static final byte consolidated_inavs = ']';
    static final byte consolidated_probable_price_of_allotment_volume_indicator = '\\';
    static final byte consolidated_spread_quality = '{';
    static final byte consolidated_message_ratio = '}';
    static final byte consolidated_depth_at_best_prices = '^';
    static final byte consolidated_effective_spread = '=';
    static final byte consolidated_trading_sniper = '<';
    static final byte consolidated_quotes_quality = '|';
    static final byte consolidated_price_leaderboard = '@';
    static final byte consolidated_spread = ';';
    static final byte consolidated_benchmark_trade = ':';
    static final byte consolidated_hour_tracker = '/';
    static final byte consolidated_big_picture = '\'';
    static final byte consolidated_local_and_global_stock_market = 'h';
    static final byte consolidated_capital_market_warrants = 'T';
    static final byte consolidated_capital_market_tracs = '[';
    static final byte consolidated_mutual_funds = '0';
    static final byte consolidated_debt_and_metals_market = '.';

    public final byte global_and_local_catalog = 'J';
    public final byte turn_over_ratio_per_security = 'o';
    public final byte securities_suspension = 'K';
    public final byte securities_unsuspension = 'l';
    public final byte negotiation_state_change_SUBRM = 'L';
    public final byte official_closing_price = 'N';

    @Override
    public String parse(ByteBuffer message, long seqNum) {
        byte type = message.get(0);
        byte[] bytes;
        BigInteger bigInt;
        String jsonSonstructor = "";
        jsonSonstructor = jsonSonstructor + "{";
        jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
        jsonSonstructor = jsonSonstructor + "\"Message\":{";

        switch (type) {
            case official_closing_price:
                count_official_closing_price++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Official Closing Price\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Closing Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";
                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case depth:
                count_depth++;

                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Depth\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 6);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Side\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 6, 7);
                    int levels = new BigInteger(bytes).intValue();
                    jsonSonstructor = jsonSonstructor + "\"Purchase or Sale Levels\":";
                    jsonSonstructor = jsonSonstructor + levels;

                    if (levels == 0) {
                        jsonSonstructor = jsonSonstructor + "}}";
                    } else {
                        jsonSonstructor = jsonSonstructor + ",";
                        jsonSonstructor = jsonSonstructor + "\"Levels\":{";
                        int offset1 = 7;
                        int offset2 = 15;
                        int offset3 = 17;
                        for (int x = 1; x <= levels; x++) {
                            jsonSonstructor = jsonSonstructor + "\"Level " + x + "\":{";
                            bytes = Arrays.copyOfRange(message.array(), offset1, offset1 + 8);
                            bigInt = new BigInteger(bytes);
                            jsonSonstructor = jsonSonstructor + "\"Price-" + x + "\":";
                            jsonSonstructor = jsonSonstructor + bigInt;
                            jsonSonstructor = jsonSonstructor + ",";

                            bytes = Arrays.copyOfRange(message.array(), offset2, offset2 + 2);
                            bigInt = new BigInteger(bytes);
                            jsonSonstructor = jsonSonstructor + "\"Number of Orders-" + x + "\":";
                            jsonSonstructor = jsonSonstructor + bigInt;
                            jsonSonstructor = jsonSonstructor + ",";

                            bytes = Arrays.copyOfRange(message.array(), offset3, offset3 + 4);
                            bigInt = new BigInteger(bytes);
                            jsonSonstructor = jsonSonstructor + "\"Volume-" + x + "\":";
                            jsonSonstructor = jsonSonstructor + bigInt;
                            if (x == levels) {
                                jsonSonstructor = jsonSonstructor + "}}}}";
                            } else {
                                jsonSonstructor = jsonSonstructor + "},";
                            }
                            offset1 = offset1 + 14;
                            offset2 = offset2 + 14;
                            offset3 = offset3 + 14;
                        }
                    }

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case probable_allocation_price:
                count_probable_allocation_price++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Probable Allocation Price\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Probable Allocation Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 17);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";
                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case continuos_auction_beginning:
                count_continuos_auction_beginning++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Continuous Auctions Beginning\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Beginning Time of Auction\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 21);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"End Auction Time\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";
                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case status_changes:
                count_status_changes++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Status Changes\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 6);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Status\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case to_middle_price_existing_quotes:
                count_to_middle_price_existing_quotes++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"To Middle Price Existing Quotes\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 6);
                    jsonSonstructor = jsonSonstructor + "\"Existing Postures\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case local_and_global_stock_markets:
                count_local_and_global_stock_markets++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Catalog for Instruments Belonging to the Local and Global Stock Market\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 7);
                    jsonSonstructor = jsonSonstructor + "\"Value Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 7, 14);
                    jsonSonstructor = jsonSonstructor + "\"Issuer\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 14, 20);
                    jsonSonstructor = jsonSonstructor + "\"Series\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 20, 28);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Last Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 28, 36);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Weighted Average Price (PPP)\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 36, 44);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Reference Date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 44, 45);
                    jsonSonstructor = jsonSonstructor + "\"Reference\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 45, 47);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Current Cupon\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 47, 48);
                    jsonSonstructor = jsonSonstructor + "\"Tradabillity\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 48, 52);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Numeric Tradability\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 52, 64);
                    jsonSonstructor = jsonSonstructor + "\"ISIN\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 64, 65);
                    jsonSonstructor = jsonSonstructor + "\"Market\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case debt_metals_and_money_market:
                count_debt_metals_and_money_market++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Catalog for Instruments Belonging to the Local and Global Stock Market\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 7);
                    jsonSonstructor = jsonSonstructor + "\"Value Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 7, 14);
                    jsonSonstructor = jsonSonstructor + "\"Issuer\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 14, 20);
                    jsonSonstructor = jsonSonstructor + "\"Issue\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 20, 28);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Issue Date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 28, 36);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Maturity date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 36, 44);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Reference Price/Rate\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 44, 52);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Reference Date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 52, 53);
                    jsonSonstructor = jsonSonstructor + "\"Reference\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 53, 55);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Current Cupon\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 55, 57);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Current Cupon\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 57, 69);
                    jsonSonstructor = jsonSonstructor + "\"ISIN\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 69, 70);
                    jsonSonstructor = jsonSonstructor + "\"Market\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 70, 78);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Current Nominal Value\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 78, 86);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Original Nominal Value\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 86, 94);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Outstanding Shares\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 94, 102);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Amont Placed\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 102, 103);
                    jsonSonstructor = jsonSonstructor + "\"Operate Price/Rate\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case capital_market_warrants:
                count_capital_market_warrants++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Catalog for Instruments Belonging to the Capital Warrants Market\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 7);
                    jsonSonstructor = jsonSonstructor + "\"Value Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 7, 14);
                    jsonSonstructor = jsonSonstructor + "\"Issuer\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 14, 20);
                    jsonSonstructor = jsonSonstructor + "\"Series\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 20, 21);
                    jsonSonstructor = jsonSonstructor + "\"Warrant Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 21, 29);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Maturity date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 29, 37);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Exercise Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 37, 45);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Reference Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 45, 53);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Reference Date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 53, 54);
                    jsonSonstructor = jsonSonstructor + "\"Reference\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 54, 66);
                    jsonSonstructor = jsonSonstructor + "\"ISIN\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case capital_market_TRACS:
                count_capital_market_TRACS++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Catalog Sent for Instruments Belonging to Capital TRACS\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"TRAC Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    jsonSonstructor = jsonSonstructor + "\"TRAC Name\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 20);
                    jsonSonstructor = jsonSonstructor + "\"Underlying Issuer\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 20, 26);
                    jsonSonstructor = jsonSonstructor + "\"Underlying Series\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 26, 34);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Securities\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 34, 42);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Excluded Securites\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 42, 50);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 50, 58);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Cash Component\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 58, 66);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Excluded Value\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 66, 74);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Number of Certificates\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 74, 82);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Theorical Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;

            case mutual_funds:
                count_mutual_funds++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Catalog Sent for Instruments Belonging to Mutual Funds\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 7);
                    jsonSonstructor = jsonSonstructor + "\"Value Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 7, 14);
                    jsonSonstructor = jsonSonstructor + "\"Issuer\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 14, 20);
                    jsonSonstructor = jsonSonstructor + "\"Series\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 20, 21);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Sector\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 21, 22);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Subsector\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 22, 23);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Business Line\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 23, 24);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Sub-Line\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 24, 34);
                    jsonSonstructor = jsonSonstructor + "\"Operating Company\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 34, 42);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Reference Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 42, 50);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Reference Date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 50, 51);
                    jsonSonstructor = jsonSonstructor + "\"Reference\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 51, 63);
                    jsonSonstructor = jsonSonstructor + "\"ISIN\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 63, 78);
                    jsonSonstructor = jsonSonstructor + "\"Rating\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case underlying_value_on_warrants:
                count_underlying_value_on_warrants++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Underlying value on Warrants Catalog\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 12);
                    jsonSonstructor = jsonSonstructor + "\"Issuer\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 12, 18);
                    jsonSonstructor = jsonSonstructor + "\"Series\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 18, 20);
                    jsonSonstructor = jsonSonstructor + "\"Value Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 20, 27);
                    jsonSonstructor = jsonSonstructor + "\"Underlying Issuer\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 27, 33);
                    jsonSonstructor = jsonSonstructor + "\"Underlying Series\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 33, 35);
                    jsonSonstructor = jsonSonstructor + "\"Underlying Value Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 35, 43);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Number of Registered Values\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case tradability:
                count_tradability++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Tradability\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Number of Operations\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 9, 17);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 17, 25);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Amount\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 25, 33);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Opening Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 33, 41);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Maximum\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 41, 49);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Minimum\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 49, 57);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Average\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 57, 65);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Last\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case trade_cancellation:
                count_trade_cancellation++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Trade Cancellation\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Trade Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case weighted_average_price_settlement_prices:
                count_weighted_average_price_settlement_prices++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Weighted Average Price - Settlement prices\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Weighted Average Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 21);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Volatility\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case best_offer:
                count_best_offer++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Best Offer\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 9);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 9, 17);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Price/Rate\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 17, 18);
                    jsonSonstructor = jsonSonstructor + "\"Offer Direction\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 18, 19);
                    jsonSonstructor = jsonSonstructor + "\"Operation Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case capital_trades:
                count_capital_trades++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Capital Trades\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Trade Time\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 17);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 17, 25);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 25, 26);
                    jsonSonstructor = jsonSonstructor + "\"Concertation Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 26, 30);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Trade Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 30, 31);
                    jsonSonstructor = jsonSonstructor + "\"Price Setter\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 31, 32);
                    jsonSonstructor = jsonSonstructor + "\"Operation Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 32, 40);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Amount\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 40, 45);
                    jsonSonstructor = jsonSonstructor + "\"Buy\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 45, 50);
                    jsonSonstructor = jsonSonstructor + "\"Sell\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 50, 51);
                    jsonSonstructor = jsonSonstructor + "\"Settlement\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 51, 52);
                    jsonSonstructor = jsonSonstructor + "\"Auction Indicator\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case event_systems:
                count_event_systems++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Events System\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 6);
                    jsonSonstructor = jsonSonstructor + "\"Event Code\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 6, 7);
                    jsonSonstructor = jsonSonstructor + "\"Market\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 7, 15);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Sending Time\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 15, 23);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Ending Time\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case virtual_trades:
                count_virtual_trades++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Virtual Trades\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 6);
                    jsonSonstructor = jsonSonstructor + "\"Status\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 6, 7);
                    jsonSonstructor = jsonSonstructor + "\"Operation Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 7, 11);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 11, 15);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 15, 16);
                    jsonSonstructor = jsonSonstructor + "\"Concertation\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 16, 21);
                    jsonSonstructor = jsonSonstructor + "\"Buy\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 21, 26);
                    jsonSonstructor = jsonSonstructor + "\"Sell\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case mutual_fund_trades:
                count_mutual_fund_trades++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Mutual Fund Trades\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Trade Date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 21);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 21, 29);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Block Value\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 29, 33);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Number of Sales Transactions\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 33, 41);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Sales Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 41, 45);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Number of Buy Transactions\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 45, 53);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Buy Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case registry_operations:
                count_registry_operations++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Registry Operations\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 6);
                    jsonSonstructor = jsonSonstructor + "\"Offer Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 6, 7);
                    jsonSonstructor = jsonSonstructor + "\"Income\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 7, 11);
                    jsonSonstructor = jsonSonstructor + "\"Value Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 11, 18);
                    jsonSonstructor = jsonSonstructor + "\"Issuer\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 18, 24);
                    jsonSonstructor = jsonSonstructor + "\"Series\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 24, 32);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Maximum Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 32, 40);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Registered Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 40, 48);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Price/Rate\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 48, 56);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Settlement Date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 56, 61);
                    jsonSonstructor = jsonSonstructor + "\"Firm\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 61, 62);
                    jsonSonstructor = jsonSonstructor + "\"Movement\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }
                break;

            //Pending::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            case derivatives_market_trades:
                count_derivatives_market_trades++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Derivatives Market Trades\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;

            case order_addition:
                count_order_addition++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Order Addition\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case order_change:
                count_order_change++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Order Change\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case execution_of_orders:
                count_execution_of_orders++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Execution of Orders\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case volume_update:
                count_volume_update++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Volume Update\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case orders_cancellation:
                count_orders_cancellation++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Orders cancellation\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case instruments_statistics:
                count_instruments_statistics++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Instruments Statistics\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case inavs:
                count_inavs++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Inavs\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case general_indexes:
                count_general_indexes++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"General Indexes\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case indexes_samples:
                count_indexes_samples++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Indexes Samples\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case open_interest:
                count_open_interest++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Open Interest\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case public_offerings:
                count_public_offerings++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Public Offerings\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case futures_operations_and_swaps_derivatives_market:
                count_futures_operations_and_swaps_derivatives_market++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Futures Operations and Swaps Derivatives Market\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case derivatives_market_strategies:
                count_derivatives_market_strategies++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Derivatives Market Strategies\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case dollar_buy_sell:
                count_dollar_buy_sell++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Dollar Buy Sell\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case short_sales_balances_per_instrument:
                count_short_sales_balances_per_instrument++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Short Sales Balances per Instrument\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case capital_markets_multiples:
                count_capital_markets_multiples++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Capital Markets Multiples\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case benchmarks:
                count_benchmarks++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Benchmarks\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case capitalization_rules:
                count_capitalization_rules++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Capitalization Rules\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case origin_closing_price:
                count_origin_closing_price++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Origin Closing Price\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
//Consolidated::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            case consolidated_relation_between_bmvbiva_instruments:
                count_consolidated_relation_between_bmvbiva_instruments++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Relation between BMV/BIVA Instruments\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_system_events:
                count_consolidated_system_events++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"System Events\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_capitals_market_trades:
                count_consolidated_capitals_market_trades++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Capitals Market Trades\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_trades_cancellation:
                count_consolidated_trades_cancellation++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Trades Cancellation\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_best_bid:
                count_consolidated_best_bid++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Best Bid\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_registration_of_order:
                count_consolidated_registration_of_order++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Registration of Order\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_execution_of_orders:
                count_consolidated_execution_of_orders++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Execution of Orders\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_cancellation_of_orders:
                count_consolidated_cancellation_of_orders++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Cancellation of Orders\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_amendment_of_orders:
                count_consolidated_amendment_of_orders++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Amendment of Orders\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_updating_of_volume:
                count_consolidated_updating_of_volume++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Updating of Volume\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_changes_in_status:
                count_consolidated_changes_in_status++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Changes in Status\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_reference_price:
                count_consolidated_reference_price++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Reference price\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_probable_price_of_allotment:
                count_consolidated_probable_price_of_allotment++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Probable Price of Allotment\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_startup_of_ongoing_bidding_sessions:
                count_consolidated_startup_of_ongoing_bidding_sessions++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Startup of Ongoing Bidding Sessions\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_existence_of_bids_at_mean_price:
                count_consolidated_existence_of_bids_at_mean_price++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Existence of Bids At Mean Price\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_weighted_average_price_ppp:
                count_consolidated_weighted_average_price_ppp++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Weighted Average Price (PPP)\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_investment_companies_trades:
                count_consolidated_investment_companies_trades++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Investment Companies Trades\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_inavs:
                count_consolidated_inavs++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"INAV's\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_probable_price_of_allotment_volume_indicator:
                count_consolidated_probable_price_of_allotment_volume_indicator++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Probable Price of Allotment/Volume Indicator\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_spread_quality:
                count_consolidated_spread_quality++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Spread Quality\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_message_ratio:
                count_consolidated_message_ratio++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Message Ratio\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_depth_at_best_prices:
                count_consolidated_depth_at_best_prices++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Depth at Best Prices\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_effective_spread:
                count_consolidated_effective_spread++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Effective Spread\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_trading_sniper:
                count_consolidated_trading_sniper++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Trading Sniper\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_quotes_quality:
                count_consolidated_quotes_quality++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Quotes Quality\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_price_leaderboard:
                count_consolidated_price_leaderboard++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Price Leaderboard\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_spread:
                count_consolidated_spread++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Spread\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_benchmark_trade:
                count_consolidated_benchmark_trade++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Benchmark Trade\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_hour_tracker:
                count_consolidated_hour_tracker++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Hour Tracker\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_big_picture:
                count_consolidated_big_picture++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Big Picture\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_local_and_global_stock_market:
                count_consolidated_local_and_global_stock_market++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Local and Global Stock Market\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_capital_market_warrants:
                count_consolidated_capital_market_warrants++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Capital Market Warrants\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_capital_market_tracs:
                count_consolidated_capital_market_tracs++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Capital Market TRACS\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_mutual_funds:
                count_consolidated_mutual_funds++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Mutual Funds\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            case consolidated_debt_and_metals_market:
                count_consolidated_debt_and_metals_market++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Debt and Metals Market\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;

            ///////////////////////
            case global_and_local_catalog:
                count_global_and_local_catalog++;
                try {

                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Global and Local Catalog\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 7);
                    jsonSonstructor = jsonSonstructor + "\"Value Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 7, 14);
                    jsonSonstructor = jsonSonstructor + "\"Issuer\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 14, 20);
                    jsonSonstructor = jsonSonstructor + "\"Serie\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 20, 28);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Reference Price\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 28, 36);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Reference Date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 36, 38);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Current Coupon\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 38, 39);
                    jsonSonstructor = jsonSonstructor + "\"Bursatility\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 39, 43);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Numeric Bursatility\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 43, 55);
                    jsonSonstructor = jsonSonstructor + "\"ISIN\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 55, 56);
                    jsonSonstructor = jsonSonstructor + "\"Market\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 56, 64);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Registered Securities\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;

            case turn_over_ratio_per_security:
                count_turn_over_ratio_per_security++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Turn Over Ratio per Security\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Date\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 17);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Operations Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 17, 25);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Operated Volume\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 25, 33);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Operated Amount INT\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 33, 37);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Operated Amount DEC\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;

            case securities_suspension:
                count_securities_suspension++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Securities Suspension\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Date for Suspension\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 14);
                    jsonSonstructor = jsonSonstructor + "\"Negotiation State\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 14, 15);
                    jsonSonstructor = jsonSonstructor + "\"Reason\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }
                break;

            case securities_unsuspension:
                count_securities_unsuspension++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Securities Unsuspension\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Date for Unsuspension\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 14);
                    jsonSonstructor = jsonSonstructor + "\"Unsuspension Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 14, 16);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Auction Duration\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;

            case negotiation_state_change_SUBRM:
                count_negotiation_state_change_SUBRM++;
                try {
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Negotiation State Change SUB-RM\",";

                    jsonSonstructor = jsonSonstructor + "\"Type\":";
                    jsonSonstructor = jsonSonstructor + "\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 1, 5);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Instrument Number\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 5, 13);
                    bigInt = new BigInteger(bytes);
                    jsonSonstructor = jsonSonstructor + "\"Date for the Change\":";
                    jsonSonstructor = jsonSonstructor + bigInt;
                    jsonSonstructor = jsonSonstructor + ",";

                    bytes = Arrays.copyOfRange(message.array(), 13, 14);
                    jsonSonstructor = jsonSonstructor + "\"Negotiation State\":";
                    jsonSonstructor = jsonSonstructor + "\"" + new String(bytes, "UTF-8") + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";

                } catch (Exception ex) {
                    jsonSonstructor = "";
                    jsonSonstructor = jsonSonstructor + "{";
                    jsonSonstructor = jsonSonstructor + "\"SeqNum\":" + seqNum + ",";
                    jsonSonstructor = jsonSonstructor + "\"Message\":{";
                    jsonSonstructor = jsonSonstructor + "\"Name\":\"Error While Decoding\"" + ex + ",";
                    jsonSonstructor = jsonSonstructor + "\"Type\":\"" + (char) type + "\"";
                    jsonSonstructor = jsonSonstructor + "}}";
                }

                break;
            default:
                jsonSonstructor = jsonSonstructor + "\"Name\": Unsupported Message Type to Decode - " + (char) type + "}}";
                break;
        }
        return jsonSonstructor;
    }
}
