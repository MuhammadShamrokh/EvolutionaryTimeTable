const labels = [];

const data = {
    labels: labels,
    datasets: [{
        label: 'Fitness Progress',
        backgroundColor: 'rgb(255, 99, 132)',
        borderColor: 'rgb(255, 99, 132)',
        data: [],
    }]
};

const config = {
    type: 'line',
    data: data,
    options: {}
};

var mainChart;

$(function () {
    mainChart = new Chart(
        document.getElementById('progressGraph'),
        config
    );
});