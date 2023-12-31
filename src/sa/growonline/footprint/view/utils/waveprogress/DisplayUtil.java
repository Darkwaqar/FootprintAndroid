/*
 * Copyright (C) RECRUIT LIFESTYLE CO., LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sa.growonline.footprint.view.utils.waveprogress;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author amyu
 */
public class DisplayUtil {

    private DisplayUtil(){}

    /**
     * ç�¾åœ¨ã�®å�‘ã��ã�Œ600dpã‚’è¶…ã�ˆã�¦ã�„ã‚‹ã�‹ã�©ã�†ã�‹
     *
     * @param context {@link Context}
     * @return 600dpã‚’è¶…ã�ˆã�¦ã�„ã‚‹ã�‹ã�©ã�†ã�‹
     */
    public static boolean isOver600dp(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels / displayMetrics.density >= 600;
    }
}
