const app = Vue.
    createApp({
        //Data: es un métedo, que retorna un objeto. Definimos variables para nuestra aplicacion.
        data() {
            return {
                coins: [],
                filteredCoins: [],
                textSearch: "",

                marketRank: [],

                email: "",
                password: "",
                firstName: "",
                lastName: "",
                emailSignUp: "",
                passwordSignUp: "",
            }
        },

        created() {
            axios.get('https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false')
                .then((response) => {
                    this.coins = response.data;
                    this.filteredCoins = response.data;
                    this.marketRank = this.coins.filter(rank => rank.market_cap_rank <= 5);

                })
        },

        methods: {
            signIn() {
                axios.post('/api/login', `email=${this.email}&password=${this.password}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    swal("Welcome to MindHub Bank!", "Enjoy it", "success", {
                        timer: 4000,
                    })
                    .then(response => location.href = '/web/accounts.html')
                })
                .catch(error => {
                    swal("Error!", error.response.data, "error", {
                        timer: 6000,
                    })
                })

            },
            signUp() {
                axios.post('/api/clients', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.emailSignUp}&password=${this.passwordSignUp}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                    .then(response =>
                        this.firstName = "",
                        this.lastName = "",
                        this.emailSignUp = "",
                        this.passwordSignUp = "",
                    )

                    .then(response => {
                        swal("Welcome to MindHub Bank!", "Enjoy it", "success", {
                            timer: 4000,
                        })
                            .then(response => window.location.reload())
                    })
                    .catch(error => {
                        swal("Error!", error.response.data, "error", {
                            timer: 6000,
                        })
                    })
            },
            newBalance(balance) {
                balance = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(balance);
                return balance;
            },

            formatNumber(dollarUSLocale) {
                dollarUSLocale = new Intl.NumberFormat('en-US').format(dollarUSLocale);
                return dollarUSLocale;
            },

            formatSymbol(symbol) {
                symbol = symbol.toUpperCase();
                return symbol;
            },

            searchCoin() {
                this.filteredCoins = this.coins.filter((coin) =>
                    coin.name.toLowerCase().includes(this.textSearch.toLowerCase()) ||
                    coin.symbol.toLowerCase().includes(this.textSearch.toLowerCase())
                );
            },

        }

    }).mount('#app')

const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

