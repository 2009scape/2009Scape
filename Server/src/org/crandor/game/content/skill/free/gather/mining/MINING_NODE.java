package org.crandor.game.content.skill.free.gather.mining;

import org.crandor.ServerConstants;
import org.crandor.game.world.repository.Repository;

import java.util.HashMap;

/**
 * Mining nodes
 * @author ceik
 */
public enum MINING_NODE{
        //Copper
        COPPER_ORE_0( 2090, 450,   (byte) 1),
        COPPER_ORE_1( 2091, 452,   (byte) 1),
        COPPER_ORE_2( 4976, 4994,  (byte) 1),
        COPPER_ORE_3( 4977, 4995,  (byte) 1),
        COPPER_ORE_4( 4978, 4996,  (byte) 1),
        COPPER_ORE_5( 9710, 18954, (byte) 1),
        COPPER_ORE_6( 9709, 32448, (byte) 1),
        COPPER_ORE_7( 9708, 32447, (byte) 1),
        COPPER_ORE_8( 11960,11555, (byte) 1),
        COPPER_ORE_9( 11961,11556, (byte) 1),
        COPPER_ORE_10(11962,11557, (byte) 1),
        COPPER_ORE_11(11937,11553, (byte) 1),
        COPPER_ORE_12(11936,11552, (byte) 1),
        COPPER_ORE_13(11938,11554, (byte) 1),
        COPPER_ORE_14(12746,450,   (byte) 1),
        COPPER_ORE_15(14906,14894, (byte) 1),
        COPPER_ORE_16(14907,14895, (byte) 1),
        COPPER_ORE_17(20448,20445, (byte) 1),
        COPPER_ORE_18(20451,20445, (byte) 1),
        COPPER_ORE_19(20446,20443, (byte) 1),
        COPPER_ORE_20(20447,20444, (byte) 1),
        COPPER_ORE_21(20408,20407, (byte) 1),
        COPPER_ORE_22(18993,19005, (byte) 1),
        COPPER_ORE_23(18992,19004, (byte) 1),
        COPPER_ORE_24(19007,19016, (byte) 1),
        COPPER_ORE_25(19006,19021, (byte) 1),
        COPPER_ORE_26(18991,19003, (byte) 1),
        COPPER_ORE_27(19008,19017, (byte) 1),
        COPPER_ORE_28(21285,21297, (byte) 1),
        COPPER_ORE_29(21284,21296, (byte) 1),
        COPPER_ORE_30(21286,21298, (byte) 1),
        COPPER_ORE_31(29231,29219, (byte) 1),
        COPPER_ORE_32(29230,29218, (byte) 1),
        COPPER_ORE_33(29232,29220, (byte) 1),
        COPPER_ORE_34(31082,37650, (byte) 1),
        COPPER_ORE_35(31081,37649, (byte) 1),
        COPPER_ORE_36(31080,37639, (byte) 1),
        COPPER_ORE_37(37647,37650, (byte) 1),
        COPPER_ORE_38(37646,37649, (byte) 1),
        COPPER_ORE_39(37645,37639, (byte) 1),
        COPPER_ORE_40(37637,37639, (byte) 1),
        COPPER_ORE_41(37688,21298, (byte) 1),
        COPPER_ORE_42(37686,21296, (byte) 1),
        COPPER_ORE_43(37687,21297, (byte) 1),
        COPPER_ORE_44(3042, 450,   (byte) 1),

        //Tin
        TIN_ORE_0( 2094, 450,   (byte) 2),
        TIN_ORE_1( 2095, 452,   (byte) 2),
        TIN_ORE_2( 3043, 450,   (byte) 2),
        TIN_ORE_3( 4979, 4994,  (byte) 2),
        TIN_ORE_4( 4980, 4995,  (byte) 2),
        TIN_ORE_5( 4981, 4996,  (byte) 2),
        TIN_ORE_6( 11957,11555, (byte) 2),
        TIN_ORE_7( 11958,11556, (byte) 2),
        TIN_ORE_8( 11959,11557, (byte) 2),
        TIN_ORE_9( 11934,11553, (byte) 2),
        TIN_ORE_10(11935,11554, (byte) 2),
        TIN_ORE_11(11933,11552, (byte) 2),
        TIN_ORE_12(14902,14894, (byte) 2),
        TIN_ORE_13(14903,14895, (byte) 2),
        TIN_ORE_14(18995,19004, (byte) 2),
        TIN_ORE_15(18994,19003, (byte) 2),
        TIN_ORE_16(18996,19005, (byte) 2),
        TIN_ORE_17(19025,19016, (byte) 2),
        TIN_ORE_18(19024,19021, (byte) 2),
        TIN_ORE_19(19026,19017, (byte) 2),
        TIN_ORE_20(21293,21296, (byte) 2),
        TIN_ORE_21(21295,21298, (byte) 2),
        TIN_ORE_22(21294,21297, (byte) 2),
        TIN_ORE_23(29227,29218, (byte) 2),
        TIN_ORE_24(29229,29220, (byte) 2),
        TIN_ORE_25(29228,29219, (byte) 2),
        TIN_ORE_26(31079,37650, (byte) 2),
        TIN_ORE_27(31078,37649, (byte) 2),
        TIN_ORE_28(31077,37639, (byte) 2),
        TIN_ORE_29(37644,37650, (byte) 2),
        TIN_ORE_30(37643,37649, (byte) 2),
        TIN_ORE_31(37642,37639, (byte) 2),
        TIN_ORE_32(37638,37639, (byte) 2),
        TIN_ORE_33(37685,21298, (byte) 2),

        //Clay
        CLAY_0( 2109, 452,   (byte) 3),
        CLAY_1( 2108, 450,   (byte) 3),
        CLAY_2( 9712, 32448, (byte) 3),
        CLAY_3( 9713, 18954, (byte) 3),
        CLAY_4( 9711, 32447, (byte) 3),
        CLAY_5( 10949,10945, (byte) 3),
        CLAY_6( 11190,21297, (byte) 3),
        CLAY_7( 11191,21298, (byte) 3),
        CLAY_8( 11189,21296, (byte) 3),
        CLAY_9( 12942,4995,  (byte) 3),
        CLAY_10(12943,4996,  (byte) 3),
        CLAY_11(12941,4994,  (byte) 3),
        CLAY_12(14904,14894, (byte) 3),
        CLAY_13(14905,14895, (byte) 3),
        CLAY_14(15505,11557, (byte) 3),
        CLAY_15(15504,11556, (byte) 3),
        CLAY_16(15503,11555, (byte) 3),
        CLAY_17(20449,20443, (byte) 3),
        CLAY_18(20450,20444, (byte) 3),
        CLAY_19(20409,20407, (byte) 3),
        CLAY_20(32429,33400, (byte) 3),
        CLAY_21(32430,33401, (byte) 3),
        CLAY_22(32431,33402, (byte) 3),
        CLAY_23(31062,37639, (byte) 3),
        CLAY_24(31063,37649, (byte) 3),
        CLAY_25(31064,37650, (byte) 3),

        //Limestone
        LIMESTONE_0(4027,12564, (byte) 4),
        LIMESTONE_1(4028,12565, (byte) 4),
        LIMESTONE_2(4029,12566, (byte) 4),
        LIMESTONE_3(4030,12567, (byte) 4),

        //Blurite
        BLURITE_ORE_0(33220,33222, (byte) 5),
        BLURITE_ORE_1(33221,33223, (byte) 5),

        //Iron
        IRON_ORE_0( 2092, 450,   (byte) 6),
        IRON_ORE_1( 2093, 452,   (byte) 6),
        IRON_ORE_2( 4982, 4994,  (byte) 6),
        IRON_ORE_3( 4983, 4995,  (byte) 6),
        IRON_ORE_4( 4984, 4996,  (byte) 6),
        IRON_ORE_5( 6943, 21296, (byte) 6),
        IRON_ORE_6( 6944, 21297, (byte) 6),
        IRON_ORE_7( 9718, 32448, (byte) 6),
        IRON_ORE_8( 9719, 18954, (byte) 6),
        IRON_ORE_9( 9717, 32447, (byte) 6),
        IRON_ORE_10(11956,11557, (byte) 6),
        IRON_ORE_11(11954,11555, (byte) 6),
        IRON_ORE_12(11955,11556, (byte) 6),
        IRON_ORE_13(14914,14895, (byte) 6),
        IRON_ORE_14(14913,14894, (byte) 6),
        IRON_ORE_15(14858,25373, (byte) 6),
        IRON_ORE_16(14857,25372, (byte) 6),
        IRON_ORE_17(14856,25371, (byte) 6),
        IRON_ORE_18(14900,14894, (byte) 6),
        IRON_ORE_19(14901,14895, (byte) 6),
        IRON_ORE_20(20423,20444, (byte) 6),
        IRON_ORE_21(20422,20443, (byte) 6),
        IRON_ORE_22(20425,20407, (byte) 6),
        IRON_ORE_23(20424,20445, (byte) 6),
        IRON_ORE_24(19002,19005, (byte) 6),
        IRON_ORE_25(19001,19004, (byte) 6),
        IRON_ORE_26(19000,19003, (byte) 6),
        IRON_ORE_27(21281,21296, (byte) 6),
        IRON_ORE_28(21283,21298, (byte) 6),
        IRON_ORE_29(21282,21297, (byte) 6),
        IRON_ORE_30(29221,29218, (byte) 6),
        IRON_ORE_31(29223,29220, (byte) 6),
        IRON_ORE_32(29222,29219, (byte) 6),
        IRON_ORE_33(32441,33400, (byte) 6),
        IRON_ORE_34(32443,33402, (byte) 6),
        IRON_ORE_35(32442,33401, (byte) 6),
        IRON_ORE_36(32452,32448, (byte) 6),
        IRON_ORE_37(32451,32447, (byte) 6),
        IRON_ORE_38(31073,37650, (byte) 6),
        IRON_ORE_39(31072,37649, (byte) 6),
        IRON_ORE_40(31071,37639, (byte) 6),
        IRON_ORE_41(37307,11552, (byte) 6),
        IRON_ORE_42(37309,11554, (byte) 6),
        IRON_ORE_43(37308,11553, (byte) 6),
        IRON_ORE_49(42034,450,   (byte) 6),

        //Silver
        SILVER_ORE_0( 2101, 452,   (byte) 7),
        SILVER_ORE_1( 2100, 450,   (byte) 7),
        SILVER_ORE_2( 6945, 21296, (byte) 7),
        SILVER_ORE_3( 6946, 21297, (byte) 7),
        SILVER_ORE_4( 9716, 18954, (byte) 7),
        SILVER_ORE_5( 9714, 32447, (byte) 7),
        SILVER_ORE_6( 9715, 32448, (byte) 7),
        SILVER_ORE_7( 11188,21298, (byte) 7),
        SILVER_ORE_8( 11186,21296, (byte) 7),
        SILVER_ORE_9( 11187,21297, (byte) 7),
        SILVER_ORE_10(15581,14834, (byte) 7),
        SILVER_ORE_11(15580,14833, (byte) 7),
        SILVER_ORE_12(15579,14832, (byte) 7),
        SILVER_ORE_13(16998,14915, (byte) 7),
        SILVER_ORE_14(16999,14916, (byte) 7),
        SILVER_ORE_15(17007,14915, (byte) 7),
        SILVER_ORE_16(17000,31061, (byte) 7),
        SILVER_ORE_17(17009,31061, (byte) 7),
        SILVER_ORE_18(17008,14916, (byte) 7),
        SILVER_ORE_19(17385,32447, (byte) 7),
        SILVER_ORE_20(17387,18954, (byte) 7),
        SILVER_ORE_21(17386,32448, (byte) 7),
        SILVER_ORE_22(29225,29219, (byte) 7),
        SILVER_ORE_23(29224,29218, (byte) 7),
        SILVER_ORE_24(29226,29220, (byte) 7),
        SILVER_ORE_25(32445,33401, (byte) 7),
        SILVER_ORE_26(32444,33400, (byte) 7),
        SILVER_ORE_27(32446,33402, (byte) 7),
        SILVER_ORE_28(31075,37649, (byte) 7),
        SILVER_ORE_29(31074,37639, (byte) 7),
        SILVER_ORE_30(31076,37650, (byte) 7),
        SILVER_ORE_31(37305,11553, (byte) 7),
        SILVER_ORE_32(37304,11552, (byte) 7),
        SILVER_ORE_33(37306,11554, (byte) 7),
        SILVER_ORE_34(37670,11552, (byte) 7),
        SILVER_ORE_35(11948,11555, (byte) 7),
        SILVER_ORE_36(11949,11556, (byte) 7),
        SILVER_ORE_37(11950,11557, (byte) 7),

        //Coal
        COAL_0( 2097, 452,   (byte) 8),
        COAL_1( 2096, 450,   (byte) 8),
        COAL_2( 4985, 4994,  (byte) 8),
        COAL_3( 4986, 4995,  (byte) 8),
        COAL_4( 4987, 4996,  (byte) 8),
        COAL_5( 4676, 450,   (byte) 8),
        COAL_6( 10948,10944, (byte) 8),
        COAL_7( 11964,11556, (byte) 8),
        COAL_8( 11965,11557, (byte) 8),
        COAL_9( 11963,11555, (byte) 8),
        COAL_10(11932,11554, (byte) 8),
        COAL_11(11930,11552, (byte) 8),
        COAL_12(11931,11553, (byte) 8),
        COAL_13(15246,15249, (byte) 8),
        COAL_14(15247,15250, (byte) 8),
        COAL_15(15248,15251, (byte) 8),
        COAL_16(14852,25373, (byte) 8),
        COAL_17(14851,25372, (byte) 8),
        COAL_18(14850,25371, (byte) 8),
        COAL_19(20410,20443, (byte) 8),
        COAL_20(20411,20444, (byte) 8),
        COAL_21(20412,20445, (byte) 8),
        COAL_22(20413,20407, (byte) 8),
        COAL_23(18999,19005, (byte) 8),
        COAL_24(18998,19004, (byte) 8),
        COAL_25(18997,19003, (byte) 8),
        COAL_26(21287,21296, (byte) 8),
        COAL_27(21289,21298, (byte) 8),
        COAL_28(21288,21297, (byte) 8),
        COAL_29(23565,21298, (byte) 8),
        COAL_30(23564,21297, (byte) 8),
        COAL_31(23563,21296, (byte) 8),
        COAL_32(29215,29218, (byte) 8),
        COAL_33(29217,29220, (byte) 8),
        COAL_34(29216,29219, (byte) 8),
        COAL_35(32426,33400, (byte) 8),
        COAL_36(32427,33401, (byte) 8),
        COAL_37(32428,33402, (byte) 8),
        COAL_38(32450,32448, (byte) 8),
        COAL_39(32449,32447, (byte) 8),
        COAL_40(31068,37639, (byte) 8),
        COAL_41(31069,37649, (byte) 8),
        COAL_42(31070,37650, (byte) 8),
        COAL_43(31168,14833, (byte) 8),
        COAL_44(31169,14834, (byte) 8),
        COAL_45(31167,14832, (byte) 8),
        COAL_46(37699,21298, (byte) 8),
        COAL_47(37698,21297, (byte) 8),
        COAL_48(37697,21296, (byte) 8),
        COAL_49(42035,452,   (byte) 8),

        //Gold
        GOLD_ORE_0( 2099, 452,   (byte) 9),
        GOLD_ORE_1( 2098, 450,   (byte) 9),
        GOLD_ORE_2( 2611, 21298, (byte) 9),
        GOLD_ORE_3( 2610, 21297, (byte) 9),
        GOLD_ORE_4( 2609, 21296, (byte) 9),
        GOLD_ORE_5( 9722, 18954, (byte) 9),
        GOLD_ORE_6( 9720, 32447, (byte) 9),
        GOLD_ORE_7( 9721, 32448, (byte) 9),
        GOLD_ORE_8( 11183,21296, (byte) 9),
        GOLD_ORE_9( 11184,21297, (byte) 9),
        GOLD_ORE_10(11185,21298, (byte) 9),
        GOLD_ORE_11(11952,11556, (byte) 9),
        GOLD_ORE_12(11953,11557, (byte) 9),
        GOLD_ORE_13(11951,11555, (byte) 9),
        GOLD_ORE_14(15578,14834, (byte) 9),
        GOLD_ORE_15(15577,14833, (byte) 9),
        GOLD_ORE_16(15576,14832, (byte) 9),
        GOLD_ORE_17(17002,14916, (byte) 9),
        GOLD_ORE_18(17003,31061, (byte) 9),
        GOLD_ORE_19(17001,14915, (byte) 9),
        GOLD_ORE_20(21291,21297, (byte) 9),
        GOLD_ORE_21(21290,21296, (byte) 9),
        GOLD_ORE_22(21292,21298, (byte) 9),
        GOLD_ORE_23(32433,33401, (byte) 9),
        GOLD_ORE_24(32432,33400, (byte) 9),
        GOLD_ORE_25(32434,33402, (byte) 9),
        GOLD_ORE_26(31065,37639, (byte) 9),
        GOLD_ORE_27(31066,37649, (byte) 9),
        GOLD_ORE_28(31067,37650, (byte) 9),
        GOLD_ORE_29(37311,11553, (byte) 9),
        GOLD_ORE_30(37310,11552, (byte) 9),
        GOLD_ORE_31(37312,11554, (byte) 9),
        GOLD_ORE_32(37471,15249, (byte) 9),
        GOLD_ORE_33(37473,15251, (byte) 9),
        GOLD_ORE_34(37472,15250, (byte) 9),
        GOLD_ORE_49(42033,452,   (byte) 9),

        //Mithril
        MITHRIL_ORE_0( 2103, 452,   (byte) 10),
        MITHRIL_ORE_1( 2102, 450,   (byte) 10),
        MITHRIL_ORE_2( 4988, 4994,  (byte) 10),
        MITHRIL_ORE_3( 4989, 4995,  (byte) 10),
        MITHRIL_ORE_4( 4990, 4996,  (byte) 10),
        MITHRIL_ORE_5( 11943,11553, (byte) 10),
        MITHRIL_ORE_6( 11942,11552, (byte) 10),
        MITHRIL_ORE_7( 11945,11555, (byte) 10),
        MITHRIL_ORE_8( 11944,11554, (byte) 10),
        MITHRIL_ORE_9( 11947,11557, (byte) 10),
        MITHRIL_ORE_10(11946,11556, (byte) 10),
        MITHRIL_ORE_11(14855,25373, (byte) 10),
        MITHRIL_ORE_12(14854,25372, (byte) 10),
        MITHRIL_ORE_13(14853,25371, (byte) 10),
        MITHRIL_ORE_14(16687,450,   (byte) 10),
        MITHRIL_ORE_15(20421,20407, (byte) 10),
        MITHRIL_ORE_16(20420,20445, (byte) 10),
        MITHRIL_ORE_17(20419,20444, (byte) 10),
        MITHRIL_ORE_18(20418,20443, (byte) 10),
        MITHRIL_ORE_19(19012,19021, (byte) 10),
        MITHRIL_ORE_20(19013,19016, (byte) 10),
        MITHRIL_ORE_21(19014,19017, (byte) 10),
        MITHRIL_ORE_22(21278,21296, (byte) 10),
        MITHRIL_ORE_23(21279,21297, (byte) 10),
        MITHRIL_ORE_24(21280,21298, (byte) 10),
        MITHRIL_ORE_25(25369,10586, (byte) 10),
        MITHRIL_ORE_26(25368,10585, (byte) 10),
        MITHRIL_ORE_27(25370,10587, (byte) 10),
        MITHRIL_ORE_28(29236,29218, (byte) 10),
        MITHRIL_ORE_29(29237,29219, (byte) 10),
        MITHRIL_ORE_30(29238,29220, (byte) 10),
        MITHRIL_ORE_31(32439,33401, (byte) 10),
        MITHRIL_ORE_32(32438,33400, (byte) 10),
        MITHRIL_ORE_33(32440,33402, (byte) 10),
        MITHRIL_ORE_34(31087,37649, (byte) 10),
        MITHRIL_ORE_35(31086,37639, (byte) 10),
        MITHRIL_ORE_36(31088,37650, (byte) 10),
        MITHRIL_ORE_37(31170,14832, (byte) 10),
        MITHRIL_ORE_38(31171,14833, (byte) 10),
        MITHRIL_ORE_39(31172,14834, (byte) 10),
        MITHRIL_ORE_40(37692,21296, (byte) 10),
        MITHRIL_ORE_41(37693,21297, (byte) 10),
        MITHRIL_ORE_42(37694,21298, (byte) 10),
        MITHRIL_ORE_49(42036,452,   (byte) 10),

        //Adamant
        ADAMANTITE_ORE_0( 2105, 452,   (byte) 11),
        ADAMANTITE_ORE_1( 2104, 450,   (byte) 11),
        ADAMANTITE_ORE_2( 4991, 4994,  (byte) 11),
        ADAMANTITE_ORE_3( 4992, 4995,  (byte) 11),
        ADAMANTITE_ORE_4( 4993, 4996,  (byte) 11),
        ADAMANTITE_ORE_5( 11941,11554, (byte) 11),
        ADAMANTITE_ORE_6( 11940,11553, (byte) 11),
        ADAMANTITE_ORE_7( 11939,11552, (byte) 11),
        ADAMANTITE_ORE_8( 14864,25373, (byte) 11),
        ADAMANTITE_ORE_9( 14863,25372, (byte) 11),
        ADAMANTITE_ORE_10(14862,25371, (byte) 11),
        ADAMANTITE_ORE_11(20417,20407, (byte) 11),
        ADAMANTITE_ORE_12(20416,20445, (byte) 11),
        ADAMANTITE_ORE_13(20414,20443, (byte) 11),
        ADAMANTITE_ORE_14(20415,20444, (byte) 11),
        ADAMANTITE_ORE_15(19020,19017, (byte) 11),
        ADAMANTITE_ORE_16(19018,19021, (byte) 11),
        ADAMANTITE_ORE_17(19019,19016, (byte) 11),
        ADAMANTITE_ORE_18(21275,21296, (byte) 11),
        ADAMANTITE_ORE_19(21276,21297, (byte) 11),
        ADAMANTITE_ORE_20(21277,21298, (byte) 11),
        ADAMANTITE_ORE_21(29233,29218, (byte) 11),
        ADAMANTITE_ORE_22(29234,29219, (byte) 11),
        ADAMANTITE_ORE_23(29235,29220, (byte) 11),
        ADAMANTITE_ORE_24(32435,33400, (byte) 11),
        ADAMANTITE_ORE_25(32437,33402, (byte) 11),
        ADAMANTITE_ORE_26(32436,33401, (byte) 11),
        ADAMANTITE_ORE_27(31083,37639, (byte) 11),
        ADAMANTITE_ORE_28(31085,37650, (byte) 11),
        ADAMANTITE_ORE_29(31084,37649, (byte) 11),
        ADAMANTITE_ORE_30(31173,14832, (byte) 11),
        ADAMANTITE_ORE_31(31174,14833, (byte) 11),
        ADAMANTITE_ORE_32(31175,14834, (byte) 11),
        ADAMANTITE_ORE_33(37468,15249, (byte) 11),
        ADAMANTITE_ORE_34(37469,15250, (byte) 11),
        ADAMANTITE_ORE_35(37470,15251, (byte) 11),
        ADAMANTITE_ORE_36(37689,21296, (byte) 11),
        ADAMANTITE_ORE_37(37690,21297, (byte) 11),
        ADAMANTITE_ORE_38(37691,21298, (byte) 11),
        ADAMANTITE_ORE_39(42037,452,   (byte) 11),

        //Runite
        RUNITE_ORE_0( 2107, 452,   (byte) 12),
        RUNITE_ORE_1( 2106, 450,   (byte) 12),
        RUNITE_ORE_2( 6669, 21296, (byte) 12),
        RUNITE_ORE_3( 6671, 21298, (byte) 12),
        RUNITE_ORE_4( 6670, 21297, (byte) 12),
        RUNITE_ORE_5( 14861,25373, (byte) 12),
        RUNITE_ORE_6( 14860,25372, (byte) 12),
        RUNITE_ORE_7( 14859,25371, (byte) 12),
        RUNITE_ORE_8( 33079,33401, (byte) 12),
        RUNITE_ORE_9( 33078,33400, (byte) 12),
        RUNITE_ORE_10(37208,21296, (byte) 12),
        RUNITE_ORE_11(37465,15249, (byte) 12),
        RUNITE_ORE_12(37466,15250, (byte) 12),
        RUNITE_ORE_13(37467,15251, (byte) 12),
        RUNITE_ORE_14(37695,21297, (byte) 12),
        RUNITE_ORE_15(37696,21298, (byte) 12),

        //Gem rocks
        GEM_ROCK_0(23567,21297, (byte) 13),
        GEM_ROCK_1(23566,21296, (byte) 13),
        GEM_ROCK_2(23568,21298, (byte) 13),

        //Rune essence
        RUNE_ESSENCE(2491, -1, (byte) 14),

        //Sandstone
        SANDSTONE(10946,10944, (byte) 15),

        //Granite
        GRANITE(10947,10945, (byte) 16),

        //Rubium?
        RUBIUM(29746,29747, (byte) 17);




        int full,empty,respawnRate,reward,level;
        double rate,experience;
        byte identifier;

        MINING_NODE(int full, int empty, byte identifier){
            this.full = full;
            this.empty = empty;
            this.identifier = identifier;
            switch(identifier & 0xFF) {
                case 1:
                case 2:
                    respawnRate = 4 | 8 << 16;
                    experience = 17.5;
                    rate = 0.05;
                    reward = identifier == 1 ? 436 : 438;
                    level = 1;
                    break;
                case 3:
                    respawnRate = 1 | 1 << 16;
                    experience = 5.0;
                    rate = 0.1;
                    reward = 434;
                    level = 1;
                    break;
                case 4:
                    respawnRate = 10 | 20 << 16;
                    experience = 26.5;
                    rate = 0.2;
                    reward = 3211;
                    level = 10;
                    break;
                case 5:
                    respawnRate = 10 | 20 << 16;
                    experience = 17.5;
                    rate = 0.2;
                    reward = 668;
                    level = 10;
                    break;
                case 6:
                    respawnRate = 15 | 25 << 16;
                    experience = 35.0;
                    rate = 0.2;
                    reward = 440;
                    level = 15;
                    break;
                case 7:
                    respawnRate = 100 | 200 << 16;
                    experience = 40.0;
                    rate = 0.3;
                    reward = 442;
                    level = 20;
                    break;
                case 8:
                    respawnRate = 50 | 100 << 16;
                    experience = 50.0;
                    rate = 0.4;
                    reward = 453;
                    level = 30;
                    break;
                case 9:
                    respawnRate = 100 | 200 << 16;
                    experience = 65.0;
                    rate = 0.6;
                    reward = 444;
                    level = 40;
                    break;
                case 10:
                    respawnRate = 200 | 400 << 16;
                    experience = 80.0;
                    rate = 0.70;
                    reward = 447;
                    level = 55;
                    break;
                case 11:
                    respawnRate = 400 | 800 << 16;
                    experience = 95.0;
                    rate = 0.85;
                    reward = 449;
                    level = 70;
                    break;
                case 12:
                    respawnRate = 1250 | 2500 << 16;
                    experience = 125.0;
                    rate = 0.95;
                    reward = 451;
                    level = 85;
                    break;
                case 13:
                    respawnRate = 166 | 175 << 16;
                    experience = 65;
                    rate = 0.95;
                    reward = 1625;
                    level = 40;
                    break;
                case 14:
                    respawnRate = 1 | 1 << 16;
                    experience = 5.0;
                    rate = 0.1;
                    reward = 1436;
                    level = 1;
                    break;
                case 15:
                    respawnRate = 30 | 60 << 16;
                    experience = 30.0;
                    rate = 0.2;
                    reward = 6971;
                    level = 35;
                    break;
                case 16:
                    respawnRate = 10 | 20 << 16;
                    experience = 50.0;
                    rate = 0.2;
                    reward = 6979;
                    level = 45;
                    break;
                case 17:
                    respawnRate = 50 | 100 << 16;
                    experience = 17.5;
                    rate = 0.6;
                    reward = 12630;
                    level = 46;
            }
        }
        private static HashMap<Integer, MINING_NODE> NODE_MAP = new HashMap<>();
        private static HashMap<Integer, Integer> EMPTY_MAP = new HashMap<>();
        static{
            for(MINING_NODE node : MINING_NODE.values()){
                NODE_MAP.putIfAbsent(node.full,node);
            }
            for(MINING_NODE node : MINING_NODE.values()){
                EMPTY_MAP.putIfAbsent(node.empty,node.full);
            }
        }

        public static MINING_NODE forId(int id){
            return NODE_MAP.get(id);
        }

        public static boolean isEmpty(int id){
            return EMPTY_MAP.get(id) != null;
        }

        public int getRewardAmount() {
                return 1;
        }

        public int getEmptyId() {
                return empty;
        }

        public int getReward() {
                return reward;
        }

        public double getExperience() {
                return experience;
        }

        public int getRespawnRate() {
                return respawnRate;
        }

        public double getRate() {
                return rate;
        }

        public int getLevel() {
                return level;
        }

        public int getId() {
                return full;
        }

        public int getMinimumRespawn() {
                return respawnRate & 0xFFFF;
        }

        public int getMaximumRespawn() {
                return (respawnRate >> 16) & 0xFFFF;
        }

        public int getRespawnDuration() {
                int minimum = respawnRate & 0xFFFF;
                int maximum = (respawnRate >> 16) & 0xFFFF;
                double playerRatio = ServerConstants.MAX_PLAYERS / Repository.getPlayers().size();
                return (int) (minimum + ((maximum - minimum) / playerRatio));
        }
}
