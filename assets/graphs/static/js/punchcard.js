
var ms = {
    "second" : 1000,
    "minute" : 60000,
    "hour" : 3600000,
    "day" : 86400000,
    "week" : 604800000
}

function Punchcard(data, w, h) {
    this.data = this.getCounts(data);
    this.w = w;
    this.h = h;
    this.padding = { top: 8, bottom: 24, right: 32, left: 100 };
    this.svg = d3.select("div#content")
        .append("svg")
        .attr("width", w)
        .attr("height", h)
        .attr("id", "punchcard");

    this.graph(this.svg);
}

Punchcard.prototype.getCounts = function(data) {
    var counts = {};

    for (i = 0; i < data.length; i++) {
        time = data[i]["weekTime"] + data[i]["dayTime"];

        if (counts[time]) {
            counts[time] += 1;
        } else {
            counts[time] = 1;
        }
    }

    var keys = Object.keys(counts);
    var result = [];

    var weekScale = d3.scale.linear()
        .domain([0, ms["week"]])
        .rangeRound([1, 7]);

    for (i = 0; i < keys.length; i++) {
        result.push({
            "time" : keys[i],
            "dayTime" : keys[i] % ms["day"],
            "weekTime" : ms["day"] * weekScale(keys[i] % ms["week"]),
            "count" : counts[keys[i]]
        })
    }

    return result;
}

Punchcard.prototype.graph = function(svg) {
    var earliest = new Date(0);
    var endDay = new Date(ms["day"]);
    var endWeek = new Date(ms["week"]);

    var xScale = d3.time.scale()
        .domain([earliest, endDay])
        .rangeRound([0 + this.padding.left, this.w - this.padding.right]);

    var pixelsPerSite = (this.h - this.padding.top - this.padding.bottom) / this.data.length;
    var padding = this.padding;
    var h = this.h;

    var xScale = d3.time.scale()
        .domain([earliest, endDay])
        .rangeRound([0 + this.padding.left, this.w - this.padding.right]);

    var yScale = d3.time.scale()
        .domain([earliest, endWeek])
        .rangeRound([0 + this.padding.top, this.h - this.padding.bottom]);

    var minMessages = d3.min(this.data, function(d) {
        return d["count"];
    });

    var maxMessages = d3.max(this.data, function(d) {
        return d["count"];
    });

    var rScale = d3.scale.linear()
        .domain([minMessages, maxMessages])
        .rangeRound([1, 6]);

    var xAxis = d3.svg.axis()
        .scale(xScale)
        .orient("bottom")
        .ticks(6)
        .tickFormat(d3.time.format("%H:%M"))
        .tickSize(4)
        .tickPadding(8);

    var yAxis = d3.svg.axis()
        .scale(yScale)
        .orient("left")
        .ticks(7)
        .tickFormat(d3.time.format("%A"))
        .tickSize(4)
        .tickPadding(8);

    svg.selectAll("circle.punchcard")
        .data(this.data)
        .enter()
        .append("circle")
        .attr("cx", function(d) {
            return xScale(new Date(d["dayTime"]));
        })
        .attr("cy", function(d) {  
            return yScale(new Date(d["weekTime"] - 58000000));
        })
        .attr("r", function(d) {
            return rScale(d["count"]);
        })
        .attr("fill", function(d) {
            return("#444");
        });

    this.svg.append("g")
        .attr("class", "axis")
        .attr("transform", "translate(0, " + (this.h - this.padding.bottom) + ")")
        .call(xAxis);

    this.svg.append("g")
        .attr("class", "axis")
        .attr("transform", "translate(100, 0)")
        .call(yAxis);
}
