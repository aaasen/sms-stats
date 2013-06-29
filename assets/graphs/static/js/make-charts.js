
$(document).ready(function () {
    var data = window.aaasenSmsData;

    var ms = {
        "second" : 1000,
        "minute" : 60000,
        "hour" : 3600000,
        "day" : 86400000,
        "week" : 604800000
    }

    var messages = data.messages.map(function(x) {
        x["time"] = x["time"] * 1000;

        var dayScale = d3.scale.linear()
            .domain([0, ms["day"]])
            .rangeRound([1, 24]);

        var weekScale = d3.scale.linear()
            .domain([0, ms["week"]])
            .rangeRound([1, 7]);

        x["dayTime"] = ms["hour"] * dayScale(x["time"] % ms["day"]);
        x["weekTime"] = ms["day"] * weekScale(x["time"] % ms["week"]);

        console.log(x.weekTime);

        return x;
    });

    var punchcard = new Punchcard(messages,
    	data.w,
    	data.h);
});
