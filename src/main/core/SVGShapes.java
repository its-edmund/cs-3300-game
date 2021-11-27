package core;

public class SVGShapes {

    //  Website for SVG (scalable vector graphics)
    // https://www.svgrepo.com/

    public static final String CAR = "M23.5 7c.276 0 .5.224.5.5v.511c0 .793-.926.989-1.616.989l-1.086-2h2.202zm-1.441 3.506c.639 1.186.946 2.252.946 3.666 0 1.37-.397 2.533-1.005 3.981v1.847c0 .552-.448 1-1 1h-1.5c-.552 0-1-.448-1-1v-1h-13v1c0 .552-.448 1-1 1h-1.5c-.552 0-1-.448-1-1v-1.847c-.608-1.448-1.005-2.611-1.005-3.981 0-1.414.307-2.48.946-3.666.829-1.537 1.851-3.453 2.93-5.252.828-1.382 1.262-1.707 2.278-1.889 1.532-.275 2.918-.365 4.851-.365s3.319.09 4.851.365c1.016.182 1.45.507 2.278 1.889 1.079 1.799 2.101 3.715 2.93 5.252zm-16.059 2.994c0-.828-.672-1.5-1.5-1.5s-1.5.672-1.5 1.5.672 1.5 1.5 1.5 1.5-.672 1.5-1.5zm10 1c0-.276-.224-.5-.5-.5h-7c-.276 0-.5.224-.5.5s.224.5.5.5h7c.276 0 .5-.224.5-.5zm2.941-5.527s-.74-1.826-1.631-3.142c-.202-.298-.515-.502-.869-.566-1.511-.272-2.835-.359-4.441-.359s-2.93.087-4.441.359c-.354.063-.667.267-.869.566-.891 1.315-1.631 3.142-1.631 3.142 1.64.313 4.309.497 6.941.497s5.301-.184 6.941-.497zm2.059 4.527c0-.828-.672-1.5-1.5-1.5s-1.5.672-1.5 1.5.672 1.5 1.5 1.5 1.5-.672 1.5-1.5zm-18.298-6.5h-2.202c-.276 0-.5.224-.5.5v.511c0 .793.926.989 1.616.989l1.086-2z";
    public static final String BOAT =
            "M226.496,190.563c2.862-0.638,5.832-0.639,8.695-0.001l113.612,25.286l-10.658-67.5c-1.112-7.041-7.171-12.233-14.3-12.252\n" +
                    "\t\tl-31.675-0.085l-5.185-64.064c-0.609-7.523-6.882-13.325-14.43-13.345l-21.56-0.058l0.1-37.157\n" +
                    "\t\tc0.03-11.045-8.9-20.024-19.946-20.054c-0.019,0-0.036,0-0.055,0c-11.02,0-19.969,8.919-19.999,19.946l-0.1,37.157l-20.988-0.056\n" +
                    "\t\tc-7.548-0.021-13.852,5.747-14.501,13.268l-5.529,64.036l-31.26-0.084c-7.128-0.019-13.216,5.14-14.365,12.175l-11.116,68.028\n" +
                    "\t\tL226.496,190.563z" +
            "M110.416,375.186c17.402-12.674,38.307-19.514,60.277-19.514c21.969,0,42.875,6.841,60.277,19.514\n" +
                    "\t\tc17.402-12.674,38.307-19.514,60.277-19.514c21.969,0,42.872,6.84,60.275,19.512c7.392-5.388,15.418-9.711,23.883-12.916\n" +
                    "\t\tl27.664-76.601c1.417-3.924,1.077-8.268-0.932-11.924c-2.01-3.656-5.495-6.27-9.567-7.177l-161.721-35.994L69.365,266.558\n" +
                    "\t\tc-4.071,0.907-7.556,3.522-9.565,7.178c-2.009,3.656-2.348,7.999-0.931,11.922l27.675,76.632\n" +
                    "\t\tC95.007,365.49,103.029,369.806,110.416,375.186z" +
            "M456.083,413.984c-11.828-11.828-27.554-18.342-44.281-18.342s-32.453,6.514-44.281,18.342\n" +
                    "\t\tc-4.273,4.273-9.954,6.626-15.997,6.626c-6.043,0-11.724-2.353-15.996-6.626c-12.209-12.208-28.246-18.312-44.282-18.312\n" +
                    "\t\tc-16.036,0-32.072,6.104-44.28,18.312c-4.273,4.273-9.954,6.626-15.997,6.626c-6.043,0-11.724-2.353-15.996-6.626\n" +
                    "\t\tc-12.209-12.208-28.246-18.312-44.282-18.312c-16.036,0-32.072,6.104-44.28,18.312c-4.41,4.41-10.204,6.615-15.996,6.615\n" +
                    "\t\tc-5.794,0-11.586-2.205-15.997-6.615c-12.208-12.208-28.245-18.312-44.281-18.312c-16.036,0-32.072,6.104-44.28,18.312\n" +
                    "\t\tc-7.811,7.811-7.811,20.474,0,28.284c7.81,7.81,20.473,7.811,28.284,0c4.41-4.41,10.203-6.615,15.997-6.615\n" +
                    "\t\ts11.586,2.205,15.997,6.616c12.208,12.208,28.244,18.312,44.28,18.312s32.073-6.104,44.281-18.312\n" +
                    "\t\tc4.41-4.411,10.204-6.616,15.997-6.616s11.586,2.205,15.996,6.616c11.827,11.827,27.554,18.341,44.28,18.341c0,0,0,0,0,0h0\n" +
                    "\t\tc16.727,0,32.453-6.514,44.281-18.342c4.41-4.41,10.204-6.615,15.997-6.615s11.586,2.205,15.996,6.616\n" +
                    "\t\tc11.827,11.827,27.554,18.341,44.28,18.341h0h0c16.727,0,32.453-6.514,44.281-18.342c4.273-4.272,9.954-6.626,15.997-6.626\n" +
                    "\t\tc6.043,0,11.724,2.354,15.997,6.626c7.811,7.81,20.473,7.811,28.284,0C463.894,434.458,463.894,421.794,456.083,413.984z";

    public static final String PLANE =
            "M6.25,11.5,12,13.16l6.32-4.59-9.07.26A.52.52,0,0,0,9,8.91L6.13,10.56A.51.51,0,0,0,6.25,11.5Z " +
            "M34.52,6.36,28.22,5a3.78,3.78,0,0,0-3.07.67L6.12,19.5l-4.57-.2a1.25,1.25,0,0,0-.83,2.22l4.45,3.53a.55.55,0,0,0,.53.09c1.27-.49,6-3,11.59-6.07l1.12,11.51a.55.55,0,0,0,.9.37l2.5-2.08a.76.76,0,0,0,.26-.45l2.37-13.29c4-2.22,7.82-4.37,10.51-5.89A1.55,1.55,0,0,0,34.52,6.36Z";

    public static final String DOG =
            "M461.554,74.997c-8.933-33.927-23.971-56.09-24.606-57.017L424.614,0l-72.622,85.024\n" +
                    "\t\t\tC318.018,74.699,282.174,73.263,256,73.263s-62.018,1.436-95.992,11.762L87.386,0L75.053,17.98\n" +
                    "\t\t\tc-0.636,0.927-15.674,23.09-24.606,57.017c-11.465,43.548-7.784,87.096,10.607,126.284c-0.569,5.869-0.869,11.91-0.869,18.134\n" +
                    "\t\t\tc0,44.977,10.322,72.655,23.391,107.695c4.388,11.764,8.923,23.927,13.627,38.165c12.107,36.644,26.729,74.414,51.543,102.418\n" +
                    "\t\t\tc23.446,26.46,53.182,41.041,90.541,44.308V404.392c-20.896-9.757-41.38-34.708-41.38-54.576\n" +
                    "\t\t\tc0-33.085,36.366-33.085,58.094-33.085s58.094,0,58.094,33.085c0,19.868-20.482,44.82-41.38,54.576V512\n" +
                    "\t\t\tc37.358-3.267,67.094-17.848,90.54-44.308c24.814-28.004,39.437-65.773,51.543-102.418c4.704-14.237,9.239-26.401,13.627-38.165\n" +
                    "\t\t\tc13.068-35.04,23.391-62.718,23.391-107.695c0-6.225-0.301-12.266-0.869-18.134C469.337,162.093,473.019,118.545,461.554,74.997z\n" +
                    "\t\t\t M205.52,247.017c-10.975,0-19.906-9.097-19.906-20.317c0-11.223,8.931-20.312,19.906-20.312c10.989,0,19.918,9.089,19.918,20.312\n" +
                    "\t\t\tC225.438,237.92,216.508,247.017,205.52,247.017z M306.33,247.169c-11.08,0-20.069-9.165-20.069-20.47\n" +
                    "\t\t\tc0-11.305,8.989-20.47,20.069-20.47c11.067,0,20.056,9.164,20.056,20.47C326.386,238.004,317.398,247.169,306.33,247.169z";


}
