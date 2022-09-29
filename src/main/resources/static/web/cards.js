const app = Vue.
    createApp({
        data() {
            return {
                clients: [],
                cards: [],
                creditCards: [],
                debitCards: [],

                dateToday: [],

                cardType: '',
                thruDate: '',
                cardColor: '',
                cardType: '',

                cardId: '',

            }
        },

        created() {
            axios.get('/api/clients/current')
                .then(response => {
                    this.clients = response.data;
                    this.cards = this.clients.cards;

                    this.creditCards = this.cards.filter((credit) => {
                        return credit.type == "CREDIT"
                    })
                    this.debitCards = this.cards.filter((debit) => {
                        return debit.type == "DEBIT"
                    })

                    this.dateToday = new Date().toISOString();

                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        methods: {
            newDate(date) {
                creationDate = new Date(date).toLocaleString();
                return creationDate;
            },

            logOut() {
                axios.post('/api/logout')
                    .then(response => location.href = '/web/index.html', { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            },
            pausedCard(card) {
                this.cardId = card.id;
                axios.patch('/api/clients/current/cards/stopcard', `cardId=${this.cardId}`)
                    .then(response => {
                        swal("The card has been paused!", "If you want to reactivate the card, contact us", "warning", {
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
        }

    }).mount('#app')