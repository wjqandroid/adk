package com.visionvera.psychologist.c.utils.cos;

/**
 * @author 刘传政
 * @date 2020/3/10 15:58
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class MiYaoResponseBean {

    /**
     * code : 0
     * errMsg : 获取临时秘钥成功
     * result : {"path":"/upload/images/dev","credentials":{"tmpSecretId":"AKID5L947lzlQnsd6-WAE4Ua2iT5iSsVGCjDLGPbKNi4k0q0PlghCCRRa5d-sk3yvvgF","tmpSecretKey":"vEg6XF84H/fjgDXn4NYRrh0hrGQFtDKXfg0Qgf3sU2w=","sessionToken":"NPCxcsyhfCkSNP9YdyfxDiBOo2YkpHoW6e1d743dd9473325ff44f19eb8aca7d7AtqrqbbVQ_a6xQdaOoQVUp1x5eMQ6v-5-_S8cZTr7Yg-b1i3UEeoDUso4vd5P9j7T81HyjJlHN80SPAq7bWiJrJlKQaD7ooWJb0QmfsObWZyYHu-woQeAZlL8rFFqnETl3O2IdrpWjBcU5604L5Ss-iLstuCmxowgujQ-T0vhUhme4nXsWmKivLSQxVB3LloFzxz0TnlgFGmlv1MMIJ34r7JG7XOU3yjpLfEhsha2cfYLDOlLh_Zh5JtQiQGZ3bishbG9IB5kPbleOZ9SksxWAxjjPfRz0V3mgGoz9B8YgjKo-X6MvDv2j47Bs62-dfJcr1AtQ6YFMiqFMfLV4yNrY1uTHBOHwjVj4_6VKQxME-Rf9UNRgTv2Q-I94vFosD1A_vDxdnGl_Q1le8CU1wQz77NepesIUwn0kOID3xRh7q4xrIxkJvi__zhYLAqcCi1hTYeYmRUjoJGR1732clYLOw7RxUr-_5771ooDWiYduk"},"requestId":"fcac491b-4646-498a-abee-07958b34fa6f","expiration":"2020-03-10T06:49:48Z","startTime":1583821188,"expiredTime":1583822988}
     */

    public int code;
    public String errMsg;
    public ResultBean result;

    public static class ResultBean {
        /**
         * path : /upload/images/dev
         * credentials : {"tmpSecretId":"AKID5L947lzlQnsd6-WAE4Ua2iT5iSsVGCjDLGPbKNi4k0q0PlghCCRRa5d-sk3yvvgF","tmpSecretKey":"vEg6XF84H/fjgDXn4NYRrh0hrGQFtDKXfg0Qgf3sU2w=","sessionToken":"NPCxcsyhfCkSNP9YdyfxDiBOo2YkpHoW6e1d743dd9473325ff44f19eb8aca7d7AtqrqbbVQ_a6xQdaOoQVUp1x5eMQ6v-5-_S8cZTr7Yg-b1i3UEeoDUso4vd5P9j7T81HyjJlHN80SPAq7bWiJrJlKQaD7ooWJb0QmfsObWZyYHu-woQeAZlL8rFFqnETl3O2IdrpWjBcU5604L5Ss-iLstuCmxowgujQ-T0vhUhme4nXsWmKivLSQxVB3LloFzxz0TnlgFGmlv1MMIJ34r7JG7XOU3yjpLfEhsha2cfYLDOlLh_Zh5JtQiQGZ3bishbG9IB5kPbleOZ9SksxWAxjjPfRz0V3mgGoz9B8YgjKo-X6MvDv2j47Bs62-dfJcr1AtQ6YFMiqFMfLV4yNrY1uTHBOHwjVj4_6VKQxME-Rf9UNRgTv2Q-I94vFosD1A_vDxdnGl_Q1le8CU1wQz77NepesIUwn0kOID3xRh7q4xrIxkJvi__zhYLAqcCi1hTYeYmRUjoJGR1732clYLOw7RxUr-_5771ooDWiYduk"}
         * requestId : fcac491b-4646-498a-abee-07958b34fa6f
         * expiration : 2020-03-10T06:49:48Z
         * startTime : 1583821188
         * expiredTime : 1583822988
         */

        public String path;
        public CredentialsBean credentials;
        public String requestId;
        public String expiration;
        public int startTime;
        public int expiredTime;

        public static class CredentialsBean {
            /**
             * tmpSecretId : AKID5L947lzlQnsd6-WAE4Ua2iT5iSsVGCjDLGPbKNi4k0q0PlghCCRRa5d-sk3yvvgF
             * tmpSecretKey : vEg6XF84H/fjgDXn4NYRrh0hrGQFtDKXfg0Qgf3sU2w=
             * sessionToken : NPCxcsyhfCkSNP9YdyfxDiBOo2YkpHoW6e1d743dd9473325ff44f19eb8aca7d7AtqrqbbVQ_a6xQdaOoQVUp1x5eMQ6v-5-_S8cZTr7Yg-b1i3UEeoDUso4vd5P9j7T81HyjJlHN80SPAq7bWiJrJlKQaD7ooWJb0QmfsObWZyYHu-woQeAZlL8rFFqnETl3O2IdrpWjBcU5604L5Ss-iLstuCmxowgujQ-T0vhUhme4nXsWmKivLSQxVB3LloFzxz0TnlgFGmlv1MMIJ34r7JG7XOU3yjpLfEhsha2cfYLDOlLh_Zh5JtQiQGZ3bishbG9IB5kPbleOZ9SksxWAxjjPfRz0V3mgGoz9B8YgjKo-X6MvDv2j47Bs62-dfJcr1AtQ6YFMiqFMfLV4yNrY1uTHBOHwjVj4_6VKQxME-Rf9UNRgTv2Q-I94vFosD1A_vDxdnGl_Q1le8CU1wQz77NepesIUwn0kOID3xRh7q4xrIxkJvi__zhYLAqcCi1hTYeYmRUjoJGR1732clYLOw7RxUr-_5771ooDWiYduk
             */

            public String tmpSecretId;
            public String tmpSecretKey;
            public String sessionToken;
        }
    }
}
