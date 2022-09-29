const app = Vue.
    createApp({
        //Data: es un métedo, que retorna un objeto. Definimos variables para nuestra aplicacion.
        data() {
            return {
                clients: [],
                coins: [],
                filteredCoins: [],
                sortCoin: [],
                textSearch: "",

            }
        },

        created() {
            axios.get('/api/clients/current')
                .then((response) => {
                    this.clients = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });


            axios.get('https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false')
                .then((response) => {
                    this.coins = response.data;
                    this.filteredCoins = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        methods: {
            logOut() {
                axios.post('/api/logout')
                    .then(response => location.href = '/web/index.html')
                    .catch(function (error) {
                        console.log(error);
                    });
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
