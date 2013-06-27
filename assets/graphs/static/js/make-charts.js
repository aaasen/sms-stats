
$(document).ready(function () {
    var data = window.aaasenSmsData;

    var messages = data.messages.map(function(x) {
        // milliseconds in a day: 86400000
        // milliseconds in a week: 604800000
        x["time"] = x["time"];

        return x;
    });

    var punchcard = new Punchcard(messages,
    	data.w,
    	data.h);
});
