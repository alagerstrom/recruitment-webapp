function formatDateBasedOnLocale(locale, date) {
    var event = new Date(date);
    var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    return event.toLocaleDateString(locale, options);
}