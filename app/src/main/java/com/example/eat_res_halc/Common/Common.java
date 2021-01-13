package com.example.eat_res_halc.Common;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.example.eat_res_halc.Model.AddonModel;
import com.example.eat_res_halc.Model.CategoryModel;
import com.example.eat_res_halc.Model.FoodModel;
import com.example.eat_res_halc.Model.SizeModel;
import com.example.eat_res_halc.Model.User;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class Common {
    public static final String USER_REFERENCES = "Users";
    public static final String POPULAR_CATEGORY_REF = "MostPopular";
    public static final String BEST_DEALS_REF = "BestDeals";
    public static final int DEFAULT_COLUMN_COUNT = 0;
    public static final int FULL_WIDTH_COLUMN = 1;
    public static final String CATEGORY_REF = "Category";
    public static final String COMMENT_REF = "Comments";
    public static final String ORDER_REF = "Orders";
    public static User currentUser;
    public static CategoryModel categorySelected;
    public static FoodModel selectedFood;
    public static String currentToken = "";
    public static String authorizeKey = "";

    public static String formatPrice(double displayPrice) {
        if (displayPrice != 0) {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            df.setRoundingMode(RoundingMode.UP);
            String finalPrice = new StringBuilder(df.format(displayPrice)).toString();

            return finalPrice.replace(".", ",");
        }

        return "0.00";
    }

    public static Double calculateExtraPrice(SizeModel userSelectedSize,
                                             List<AddonModel> userSelectedAddon) {
        Double result = 0.0;

        if (userSelectedSize == null && userSelectedAddon == null)
            return result;

        if (userSelectedSize == null) {
            if (userSelectedAddon != null) {
                for (AddonModel model : userSelectedAddon)
                    result += model.getPrice();
            }
            return result;
        }

        result = userSelectedSize.getPrice() * 1.0;

        if (userSelectedAddon != null) {
            for (AddonModel model : userSelectedAddon)
                result += model.getPrice();
        }

        return result;
    }

    public static void setSpanString(String welcome, String name, TextView view) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(welcome);
        SpannableString spannableString = new SpannableString(name);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(spannableString);
        view.setText(builder, TextView.BufferType.SPANNABLE);
    }

    public static String createOrderNumber() {
        return new StringBuilder()
                .append(System.currentTimeMillis()) //current time in milliseconds
                .append(Math.abs(new Random().nextInt())) //add random number to block same order
                .toString();
    }

    public static String buildToken(String authorizeKey) {
        return new StringBuilder("Bearer").append(" ").append(authorizeKey).toString();
    }

    public static String getDateOfWeek(int i) {
        switch (i) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                return "Unknown day";
        }
    }

    public static String convertStatusToText(int orderStatus) {
        switch (orderStatus) {
            case 0:
                return "Placed";
            case 1:
                return "Shipping";
            case 2:
                return "Shipped";
            case -1:
                return "Cancelled";
            default:
                return "Unknown status";
        }
    }
}
