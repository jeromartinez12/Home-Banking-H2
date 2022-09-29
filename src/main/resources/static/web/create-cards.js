const app = Vue.

    createApp({
        data() {
            return {
                cardType: [],
                cardColor: [],
                clients: [],
                cards: [],
                creditCards: [],
                debitCards: [],
            }
        },

        created() {
            axios.get('/api/clients/current')
                .then((response) => {
                    this.clients = response.data;
                    this.cards = this.clients.cards;
                    this.creditCards = this.cards.filter((credit)=>{
                        return credit.type == "CREDIT"
                    })
                    this.debitCards = this.cards.filter((debit)=>{
                        return debit.type == "DEBIT"
                    })
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        methods: {
            newDate(creationDate) {
                creationDate = new Date(creationDate).toLocaleString();

                return creationDate;
            },
            newBalance(balance) {
                balance = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(balance);
                return balance;
            },
            logOut() {
                axios.post('/api/logout')
                    .then(response => location.href = '/web/index.html')
            },
            newCard() {
                axios.post("/api/clients/current/cards", `cardType=${this.cardType}&cardColor=${this.cardColor}`,
                    { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                    .then(response => {
                        swal("New card created successfully!", "You can see it in the cards section", "success", {
                            timer: 4000,
                        })
                    .then(response => window.location.reload())
                    })
                    .catch(error => {
                        swal("Error!", error.response.data, "error", {
                            timer: 6000,
                        })
                    });
            }
        }

    }).mount('#app')