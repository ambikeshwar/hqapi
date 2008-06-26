class ErrorHandler {
    // List of all possible error conditions
    static codes =
    [LoginFailure: "The given username and password could not be validated",
     ObjectNotFound: "The requested object could not be found",
     ObjectExists: "The given object already exists",
     InvalidParameters: "The given parameters are incorrect",
     UnkownError: "An unexpected error occured"]

    static void printSuccessStatus(xmlOut) {
        xmlOut.Status("Success")
    }

    static void printFailureStatus(xmlOut, code) {
        def reason = codes.get(code)
        if (reason == null) {
            throw new IllegalArgumentException("Invalid ErrorCode")
        }

        xmlOut.Status("Failure")
        xmlOut.Errors() {
            xmlOut.Error() {
                ErrorCode(code)
                ReasonText(reason)
            }
        }
    }
}