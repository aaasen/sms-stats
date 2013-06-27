
function Punchcard(data, w, h) {
    this.data = data;
    this.w = w;
    this.h = h;
    this.padding = { top: 0, bottom: 32, right: 32, left: 32 };
    this.svg = d3.select("div#content")
        .append("svg")
        .attr("width", w)
        .attr("height", h)
        .attr("id", "punchcard");

    this.graph(this.svg);
}

Punchcard.prototype.graph = function(svg) {
    var earliest = new Date(d3.min(this.data, function(d) {
        return d["time"];
    }));

    var latest = new Date(d3.max(this.data, function(d) {
        return d["time"];
    }));

    var xScale = d3.time.scale()
        .domain([earliest, latest])
        .rangeRound([0 + this.padding.left, this.w - this.padding.right]);

    var pixelsPerSite = (this.h - this.padding.top - this.padding.bottom) / this.data.length;
    var padding = this.padding;
    var h = this.h;

    var yScale = d3.scale.ordinal()
        .range(this.data.map(function(x, i) {
            return padding.top + ((h - padding.top - padding.bottom) / 2.0);
        })
    );

    var xAxis = d3.svg.axis()
        .scale(xScale)
        .orient("bottom")
        .ticks(6)
        .tickFormat(d3.time.format("%A"))
        .tickSize(0)
        .tickPadding(8);

    svg.selectAll("circle.punchcard")
        .data(this.data)
        .enter()
        .append("circle")
        .attr("cx", function(d) {
            return xScale(new Date(d["time"]));
        })
        .attr("cy", function(d) {
            return yScale(d["address"]);
        })
        .attr("r", function(d) {
            return 2;
        })
        .attr("fill", function(d) {
            return("#" + md5(d["address"]).substring(0, 6));
        });

    this.svg.append("g")
        .attr("class", "axis")
        .attr("transform", "translate(0, " + (this.h - this.padding.bottom) + ")")
        .call(xAxis);
}
